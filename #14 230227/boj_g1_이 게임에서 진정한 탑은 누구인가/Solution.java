import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    static class Pair<A, B> {
        final A first;
        final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    // 잭스 enum
    // 아무것도, 평타, 반격을 String 타입으로 define
    enum JAX {
        NONE(""), ATTACK("attack"), COUNTER_STRIKE("counter strike");
        private final String text;

        JAX(String text) {
            this.text = text;
        }

        public String toString() {
            return text;
        }
    }

    // 피오라 enum
    // 상태만 나타냄
    // 아무것도, 공격, 응수, 응수 후 공격
    enum FIORA {
        NONE, ATTACK, RIPOSTE, RIPOSTE_ATTACK;
    }

    // 잭스 체력
    static int jaxHp;
    // 피오라 체력
    static int fioraHp;
    // 잭스 평타, 스킬 데미지
    static int jaxAttackDmg, jaxSkillDmg;
    // 피오라 평타, 스킬 데미지
    static int fioraAttackDmg, fioraSkillDmg;
    // 잭스가 이기는 최소프레임
    // 최대 300프레임이므로
    static int minFrame = 301;
    // 잭스가 이길 때 최대 체력
    static int maxJaxHp = 0;

    // 피오라의 행동을 저장하는 배열
    static FIORA[] fioraActions;

    // dp[x][y][z] = a
    // x프레임에서 피오라 체력이 y일 때 잭스가 z 행동을 할 때의 잭스의 체력 a
    static int[][][] dp;

    // 잭스의 행동을 기록할 스택
    // 앞 뒤로 뽑아야 하므로 LinkedList (큐)의 형태를 사용
    static LinkedList<Pair<Integer, JAX>> ans = new LinkedList<>();
    static LinkedList<Pair<Integer, JAX>> jaxActions = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 잭스 체력, 피오라 체력
        jaxHp = Integer.parseInt(st.nextToken());
        fioraHp = Integer.parseInt(st.nextToken());

        // 잭스 평타, 스킬 데미지, 피오라 평타, 스킬 데미지
        st = new StringTokenizer(br.readLine());
        jaxAttackDmg = Integer.parseInt(st.nextToken());
        jaxSkillDmg = Integer.parseInt(st.nextToken());
        fioraAttackDmg = Integer.parseInt(st.nextToken());
        fioraSkillDmg = Integer.parseInt(st.nextToken());

        // 피오라가 공격할 횟수
        int fioraAttackCount = Integer.parseInt(br.readLine());
        // 300 프레임 + 응수후 공격을 포함하는 행동 배열
        fioraActions = new FIORA[minFrame + 15];
        // 초기화
        Arrays.fill(fioraActions, FIORA.NONE);
        // 피오라의 공격 저장
        // 프레임, 공격 타입
        for (int i = 0; i < fioraAttackCount; i++) {
            st = new StringTokenizer(br.readLine());
            int frame = Integer.parseInt(st.nextToken());
            String action = st.nextToken();
            if (action.equals("attack")) {
                // 5프레임동안 공격하므로 4칸 뒤의 프레임에 행동을 저장.
                fioraActions[frame + 4] = FIORA.ATTACK;
            } else if (action.equals("riposte")) {
                // 10프레임을 차지하므로 10칸을 채운다
                Arrays.fill(fioraActions, frame, frame + 10, FIORA.RIPOSTE);
                // 그리고 10번째에 응수로 공격한다.
                fioraActions[frame + 9] = FIORA.RIPOSTE_ATTACK;
            }
        }
        dp = new int[minFrame + 1][fioraHp + 1][3];
        for (int i = 0; i <= minFrame; i++) {
            for (int j = 0; j <= fioraHp; j++) {
                // 잭스의 모든 체력을 초기화
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        solve();
    }

    static void solve() {
        // 0프레임부터 시작
        dfs(0, jaxHp, fioraHp);
        // 비어있으면 답이 없다.
        if (ans.isEmpty()) {
            System.out.println("NO");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("YES\n");
        while (!ans.isEmpty()) {
            Pair<Integer, JAX> poll = ans.poll();
            sb.append(poll.first).append(' ').append(poll.second.toString()).append('\n');
        }
        System.out.println(sb);
    }

    // frame 일 때 jax와 fiora의 hp를 통해 연산
    static void dfs(int frame, int jaxHp, int fioraHp) {
        // 최소 프레임보다 크면 탐색할 가치가 없으므로
        if (frame > minFrame) {
            return;
        }
        // 피오라 체력이 0이하라면
        // 답변을 저장하고 갱신해야 한다
        else if (fioraHp <= 0) {
            if (minFrame > frame) {
                minFrame = frame;
                // 최소프레임에서의 체력이 최대체력이다.
                maxJaxHp = jaxHp;
                // 답변갱신
                updateAnswer();
            }
            // 만약 잭스 체력이 이전보다 더 많다면
            else if (jaxHp > maxJaxHp) {
                // 잭스 체력만 갱신
                maxJaxHp = jaxHp;
                updateAnswer();
            }
            return;
        }

        // 모두 통과했다면
        // 잭스는 아직 행동을 해야 한다.
        // 잭스가 공격을 안할 때
        none(frame, jaxHp, fioraHp);
        // 잭스가 평타
        attack(frame, jaxHp, fioraHp);
        // 잭스가 반격
        counterStrike(frame, jaxHp, fioraHp);
    }

    // 답변 갱신
    private static void updateAnswer() {
        ans.clear();
        ans.addAll(jaxActions);
    }


    // 아무것도 안할 때
    static void none(int frame, int jaxHp, int fioraHp) {
        int jaxCurrentHp;
        FIORA action = fioraActions[frame];
        if (action.equals(FIORA.NONE) || action.equals(FIORA.RIPOSTE)) {
            jaxCurrentHp = jaxHp;
        } else if (action.equals(FIORA.ATTACK)) {
            jaxCurrentHp = jaxHp - fioraAttackDmg;
        } else if (action.equals(FIORA.RIPOSTE_ATTACK)) {
            jaxCurrentHp = jaxHp - fioraSkillDmg;
        } else
            throw new IllegalArgumentException("Invalid action");

        // 기저 조건
        // 1. 잭스의 체력이 0이하가 된다.
        // 2. 이전에 탐색한 기록보다 체력이 더 많이 잃었다. -> 잭스의 체력은 무조건 많아야 이득이므로
        if (jaxCurrentHp <= dp[frame][fioraHp][JAX.NONE.ordinal()] || jaxCurrentHp <= 0) {
            return;
        }
        // 값 갱신
        dp[frame][fioraHp][JAX.NONE.ordinal()] = jaxCurrentHp;

        // 탐색 계속
        dfs(frame + 1, jaxCurrentHp, fioraHp);
    }

    // 평타
    static void attack(int frame, int jaxHp, int fioraHp) {
        int jaxCurrentHp = jaxHp;
        // 공격은 총 5프레임이 소모된다.
        int nextFrame = frame + 5;
        for (int i = frame; i < nextFrame; i++) {
            FIORA action = fioraActions[i];
            if (action.equals(FIORA.NONE) || action.equals(FIORA.RIPOSTE)) {
                jaxCurrentHp -= 0;
            } else if (action.equals(FIORA.ATTACK)) {
                jaxCurrentHp -= fioraAttackDmg;
            } else if (action.equals(FIORA.RIPOSTE_ATTACK)) {
                jaxCurrentHp -= fioraSkillDmg;
            } else
                throw new IllegalArgumentException("Invalid action");

        }

        // 기저 조건
        // 1. 잭스의 체력이 0이하가 된다.
        // 2. 이전에 탐색한 기록보다 체력이 더 많이 잃었다. -> 잭스의 체력은 무조건 많아야 이득이므로
        if (jaxCurrentHp <= dp[frame][fioraHp][JAX.ATTACK.ordinal()] || jaxCurrentHp <= 0) {
            return;
        }
        // 값 갱신
        dp[frame][fioraHp][JAX.ATTACK.ordinal()] = jaxCurrentHp;
        // 스택에 저장
        jaxActions.addLast(new Pair<Integer, JAX>(frame, JAX.ATTACK));
        dfs(
                nextFrame,
                jaxCurrentHp,
                fioraHp - (fioraActions[nextFrame - 1] == FIORA.RIPOSTE ? 0 : jaxAttackDmg)
                // 잭스가 공격을 했기 때문에 피오라는 '응수 중'이 아니면 체력이 닳는다.
        );
        jaxActions.pollLast();
    }


    // 반격
    static void counterStrike(int frame, int jaxHp, int fioraHp) {
        int jaxCurrentHp = jaxHp;
        // 반격은 15 프레임이 소모된다. 그 후 공격한다.
        // 만약 끝나고 직후 공격이 있으면 아파요
        int nextFrame = frame + 15;
        FIORA action = fioraActions[nextFrame - 1];
        if (action.equals(FIORA.NONE) || action.equals(FIORA.RIPOSTE)) {
            jaxCurrentHp = jaxHp;
        } else if (action.equals(FIORA.ATTACK)) {
            jaxCurrentHp = jaxHp - fioraAttackDmg;
        } else if (action.equals(FIORA.RIPOSTE_ATTACK)) {
            jaxCurrentHp = jaxHp - fioraSkillDmg;
        } else
            throw new IllegalArgumentException("Invalid action");

        // 기저 조건
        // 이전과 동일
        if (jaxCurrentHp <= dp[frame][fioraHp][JAX.COUNTER_STRIKE.ordinal()] || jaxCurrentHp <= 0) {
            return;
        }
        // 값 갱신
        dp[frame][fioraHp][JAX.COUNTER_STRIKE.ordinal()] = jaxCurrentHp;
        // 스택에 저장
        jaxActions.addLast(new Pair<Integer, JAX>(frame, JAX.COUNTER_STRIKE));
        dfs(
                nextFrame,
                jaxCurrentHp,
                fioraHp - (fioraActions[nextFrame - 1] == FIORA.RIPOSTE ? 0 : jaxSkillDmg)
                // 잭스가 반격 공격을 했기 때문에 피오라는 '응수 중'이 아니면 체력이 닳는다.
        );
        jaxActions.pollLast();
    }
}
