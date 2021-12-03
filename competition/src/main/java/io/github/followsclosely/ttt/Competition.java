package io.github.followsclosely.ttt;

import io.github.followsclosely.MinMaxAI;
import io.github.followsclosely.MinMaxWithPruningAI;
import io.github.followsclosely.StinkAI;
import io.github.followsclosely.ttt.ai.DummyAI;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Competition {

    private List<ArtificialIntelligenceDecorator> ais = new ArrayList<>();

    public static void main(String[] args) {
        new Competition()
                .add(new DummyAI(Piece.X))
                .add(new StinkAI(Piece.X))
                .add(new MinMaxAI(Piece.X))
                .add(new MinMaxWithPruningAI(Piece.X))
                .run();
    }

    public Competition add(ArtificialIntelligence ai) {
        ais.add(new ArtificialIntelligenceDecorator(ai));
        return this;
    }

    public void run() {

        int size = ais.size();
        int numberOfSimulations = 1000;
        final ExecutorService executorService = Executors.newFixedThreadPool(6);
        Simulation[][] matches = new Simulation[size][size];

        try {

            List<Future<Simulation>> futures = new ArrayList<>();

            for (int x = 0; x < size; x++) {
                ArtificialIntelligence player1 = ais.get(x);
                for (int y = 0; y < size; y++) {
                    if (x != y) {
                        ArtificialIntelligence player2 = ais.get(y);

                        System.out.println(player1 + " vs. " + player2);
                        final Simulation match = matches[x][y] = new Simulation()
                                .addArtificialIntelligence(player1)
                                .addArtificialIntelligence(player2)
                                .number(numberOfSimulations);

                        futures.add(executorService.submit(() -> {
                            match.run();
                            return match;
                        }));
                    }
                }
            }

            for (Future<Simulation> future : futures) {
                try {
                    future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Something went wrong. This shouldn't happen.");
                }
            }

            printWithVelocity(matches);
        } finally {
            executorService.shutdown();
        }

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