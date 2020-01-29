package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.mm.MiniMax;
import io.github.followsclosely.ttt.mm.MiniMaxWithAlphaBeta;
import io.github.followsclosely.ttt.stink.StinkAI;
import io.github.followsclosely.ttt.ai.DummyAI;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Competition class runs a matrix of simulations facing each ai against each ai.
 */
public class Competition {

    private final List<ArtificialIntelligenceDecorator> ais = new ArrayList<>();

    public static void main(String[] args) {
        new Competition()
                .add(new DummyAI())
                .add(new StinkAI())
                .add(new MiniMax())
                .add(new MiniMaxWithAlphaBeta())
                .run();
    }

    public Competition add(ArtificialIntelligence ai) {
        ais.add(new ArtificialIntelligenceDecorator(ai));
        return this;
    }

    public void run() {

        int size = ais.size();
        int numberOfSimulations = 10000;
        Simulation[][] matches = new Simulation[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (x != y) {
                    matches[x][y] = new Simulation().ai1(ais.get(x)).ai2(ais.get(y)).number(numberOfSimulations).run();
                }
            }
        }

        printWithVelocity(matches);
    }

    private void printWithVelocity(Simulation[][] matches) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        VelocityContext context = new VelocityContext();
        context.put("matches", matches);
        context.put("ais", ais);

        Template t = velocityEngine.getTemplate("./competition/src/main/java/index.vm");

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        System.out.println(writer);
    }
}