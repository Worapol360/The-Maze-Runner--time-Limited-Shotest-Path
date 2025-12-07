package org.the.maze.runner.controller;

import org.the.maze.runner.App;
import org.the.maze.runner.algorithm.*;
import org.the.maze.runner.model.*;
import org.the.maze.runner.ui.GridView;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane; // Using StackPane as the FXML container is often best

public class GridController {

    // IMPORTANT: Assuming gridPane is a container (like VBox, HBox, or StackPane)
    // in your FXML that will hold the actual visualization surface.
    @FXML
    private Pane gridPane; // The FXML-injected container for the visualization

    private GridView gridView;

    // Define tile size for visualization
    private static final int maxWidth = 478;
    private static final int maxHeight = 478;

    @FXML
    public void initialize() {
        this.gridView = new GridView(maxWidth, maxHeight);
        Pane initialVisualization = gridView.draw(App.getMaze());

        // CRITICAL: Call the update function to place the new Pane into the FXML
        // container
        updateVisualizationPane(initialVisualization);
    }

    // --- Core Function to Update the FXML Pane ---

    /**
     * Replaces the current content of the FXML-injected gridPane with the new
     * visualization Pane.
     * 
     * @param newPane The Pane containing the freshly drawn maze/path.
     */
    private void updateVisualizationPane(Pane newPane) {
        if (gridPane == null) {
            System.err.println("Error: FXML gridPane container is null.");
            return;
        }
        // 1. Clear all existing children from the container
        gridPane.getChildren().clear();

        // 2. Add the new Pane containing the visualization
        gridPane.getChildren().add(newPane);

        // Optional: Ensure the new Pane is stretched to fill the container if gridPane
        // is a layout like StackPane/BorderPane
        // newPane.maxWidth(gridPane.getWidth());
        // newPane.maxHeight(gridPane.getHeight());
    }

    // --- Algorithm Runners ---

    @FXML
    public void runBFS() {
        Pane pathVisualization = gridView.drawPath(new BFSAlgorithm());
        updateVisualizationPane(pathVisualization);
    }

    @FXML
    public void runDijkstra() {
        Pane pathVisualization = gridView.drawPath(new DijkstraAlgorithm());
        updateVisualizationPane(pathVisualization);
    }

    @FXML
    public void runAStar() {
        Pane pathVisualization = gridView.drawPath(new AStarAlgorithm());
        updateVisualizationPane(pathVisualization);
    }

    // --- Path Drawing Logic ---

    public void goBack() {
        App.setRoot("main-view");
    }

    public void goInitialize() {
        App.setRoot("input-view");
    }
}