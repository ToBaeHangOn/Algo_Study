import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int hp[], damage[], m, Fiora_state[];
    // Fiora_state < -1 : 무적, 0 : 아무 행동 안함, 2 : 평타, 3 : 응수 >
    static List<Jax> jaxList = new ArrayList<>();
    static int dp[][];
    // Jax_hp = dp[Fiora_hp][Frame]
    static class Jax implements Comparable<Jax>{
        int hp;
        int win_frame = Integer.MAX_VALUE;
        Map<Integer, String> actions;
        public Jax(int hp) {
            this.hp = hp;
            actions = new HashMap<>();
        }
        public Jax(int hp, Map<Integer, String> actions) {
            this.hp = hp;
            this.actions = new HashMap<>(actions);
        }
        @Override
        public int compareTo(Jax o){
            if(this.win_frame < o.win_frame) return -1;
            else if(this.win_frame == o.win_frame && this.hp > o.hp) return -1;
            else return 1;
        }
    }
    static void input() throws IOException{
        hp = new int[2];
        damage = new int[4];
        Fiora_state = new int[315];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2; i++){
            hp[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[hp[1] + 1][301];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            damage[i] = Integer.parseInt(st.nextToken());
        }
        // 0 : 잭스 평타, 1 : 잭스 반격, 2 : 피오라 평타, 3 : 피오라 응수
        m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int frame = Integer.parseInt(st.nextToken());
            String act = st.nextToken();
            if(act == "attack"){
                Fiora_state[frame + 4] = 2; // 평타
            }
            else{
                for(int j = frame; j < frame + 9; j++){
                    Fiora_state[j] = -1; // 응수 무적
                }
                Fiora_state[frame + 9] = 3; // 응수
            }
        }
    }
    static void solve(){
        Jax jax = new Jax(hp[0]);
        dfs(jax, hp[1], 0, "none");
        if(jaxList.size() == 0){
            sb.append("NO");
            return; }
        else {
            sb.append("YES").append("\n");
            Collections.sort(jaxList); // 기저 조건을 기준으로 이긴 잭스들 정렬
            for (int i : jaxList.get(0).actions.keySet()) {
                sb.append(i).append(" ").append(jaxList.get(0).actions.get(i)).append("\n");
            }
        }
    }
    static void dfs(Jax jax, int Fiora_hp, int cur_frame, String action){
        if(cur_frame >= 300) return;
        int cur_Fiora_state = Fiora_state[cur_frame];
        // 평타나 응수 데미지 들어올 때
        if(cur_Fiora_state == 2 || cur_Fiora_state == 3){
            jax.hp -= damage[cur_Fiora_state];
        }
        // 졌으면 더 탐색할 필요 X
        if(jax.hp <= 0) return;

        // 잭스가 공격하고 피오라가 무적이 아닐 때
        if((action == "attack" || action == "counter strike") && Fiora_state[cur_frame] != -1){
            int cur_damage;
            if(action == "attack") cur_damage = damage[0];
            else cur_damage = damage[1];
            Fiora_hp -= cur_damage;
        }
        // 이긴 잭스 넣기
        if(Fiora_hp <= 0) {
            jax.win_frame = cur_frame;
            jaxList.add(jax);
            return;
        }

        // 피오라의 체력과 프레임이 같을 때 잭스의 hp가 이전보다 작으면
        // 더 탐색 할 필요가 없으므로 return
        if(jax.hp > dp[Fiora_hp][cur_frame]){
            dp[Fiora_hp][cur_frame] = jax.hp;
        }
        else return;

        // 가만히 있기
        Jax noneJax = new Jax(jax.hp, jax.actions);
        dfs(noneJax, Fiora_hp, cur_frame + 1, "none");

        // 평타나 반격 데미지가 들어간 프레임에 동시에 바로 평타나 반격 준비를 할 수 없기 때문에
        if(action == "attack" || action == "counter strike") cur_frame++;

        // 평타 치기
        int damage_until_delay = 0;
        for(int i = cur_frame + 1; i < cur_frame + 4; i++){ // 평타 딜레이 동안 피오라의 행동 체크
            cur_Fiora_state = Fiora_state[i];
            if(cur_Fiora_state == 2 || cur_Fiora_state == 3){ // 평타나 응수 데미지 들어올 때
                damage_until_delay += damage[cur_Fiora_state];
            }
        }
        if(damage_until_delay < jax.hp){ // 평타 딜레이 동안 죽으면 탐색할 필요 X
            // 딜레이 동안 맞은 만큼 체력이 깎인 잭스 객체 생성
            Jax attackJax = new Jax(jax.hp - damage_until_delay, jax.actions);
            attackJax.actions.put(cur_frame, "attack");
            dfs(attackJax, Fiora_hp, cur_frame + 4, "attack");
        }

        // 반격 쓰기
        Jax counterJax = new Jax(jax.hp, jax.actions);
        counterJax.actions.put(cur_frame, "counter strike");
        dfs(counterJax, Fiora_hp, cur_frame + 14, "counter strike");
    }
    public static void main(String[] argrs) throws IOException {
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
