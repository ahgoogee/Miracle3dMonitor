package miracle3d.benchmark;

public enum BenchmarkType {
    RUN("RunChallenge");
    private String decisionmaker;


    BenchmarkType(String _decisionmaker)
    {
        this.decisionmaker=_decisionmaker;
    }

    public String getDecisionmaker() {
        return decisionmaker;
    }
}
