package Modules;

import java.text.DecimalFormat;

public class Benchmark {
    double StartSeconds;
    long StartMilliseconds;
    private DecimalFormat RoundFormat;

    public Benchmark() {
        long Milliseconds = System.currentTimeMillis();
        this.StartSeconds = Milliseconds * 0.001;
        this.StartMilliseconds = Milliseconds;
        this.RoundFormat = new DecimalFormat("###.#####");
    }

    public Benchmark(double CustomStart) {
        this.StartSeconds = CustomStart;
        this.StartMilliseconds = (long) (CustomStart * 1000);
        this.RoundFormat = new DecimalFormat("###.#####");
    }

    public Benchmark(String FormatPattern) {
        long Milliseconds = System.currentTimeMillis();
        this.StartSeconds = Milliseconds * 0.001;
        this.StartMilliseconds = Milliseconds;
        this.RoundFormat = new DecimalFormat(FormatPattern);
    }

    public Benchmark(double CustomStart, String FormatPattern) {
        this.StartSeconds = CustomStart;
        this.StartMilliseconds = (long) (CustomStart * 1000);
        this.RoundFormat = new DecimalFormat(FormatPattern);
    }

    public void Reset() {
        long Milliseconds = System.currentTimeMillis();
        this.StartSeconds = Milliseconds * 0.001;
        this.StartMilliseconds = Milliseconds;
    }

    public void ChangeRounding(String FormatPattern) {
        this.RoundFormat = new DecimalFormat(FormatPattern);
    }

    public double GetDeltaInSeconds() {
        double Delta = ((System.currentTimeMillis() * 0.001) - this.StartSeconds);
        return Double.parseDouble(this.RoundFormat.format(Delta));
    }

    public long GetDeltaInMilliseconds() {
        return System.currentTimeMillis() - this.StartMilliseconds;
    }
}
