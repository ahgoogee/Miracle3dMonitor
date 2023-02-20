package miracle3d.benchmark.playercontroller;

import miracle3d.benchmark.BenchmarkType;

import java.nio.file.Path;

public class PlayerControllerParameters {
    private Path scriptPath;
    private BenchmarkType benchmarkType;


    public PlayerControllerParameters(Path scriptPath, BenchmarkType benchmarkType) {
        this.scriptPath = scriptPath;
        this.benchmarkType = benchmarkType;
    }

    public Path getScriptPath() {
        return scriptPath;
    }

    public BenchmarkType getBenchmarkType() {
        return benchmarkType;
    }
}
