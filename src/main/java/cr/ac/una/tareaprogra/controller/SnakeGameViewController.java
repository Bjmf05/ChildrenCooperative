package cr.ac.una.tareaprogra.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SnakeGameViewController extends Controller implements Initializable {

    @FXML
    private Canvas cnvGame;
    @FXML
    private Button btnReset;
    private static final int DEFAULT_SPEED = 3;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 13;
    private static final int CORNER_SIZE = 25;

    private int speed = DEFAULT_SPEED;
    private int foodColorIndex = 0;
    private int foodX = 0;
    private int foodY = 0;
    private int score = 0;
    private boolean gameOver = false;
    private Random random = new Random();
    private AnimationTimer gameLoop;

    private enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
    private Direction direction = Direction.LEFT;
    private List<Corner> snake = new ArrayList<>();

    public static class Corner {

        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initGame();

    }

    @FXML
    private void onActionBtnReset(ActionEvent event) {
        stopGameLoop();
        initGame();
        setupInputHandlers();
        startGameLoop();
    }

    @Override
    public void initialize() {

    }
//Funcion para iniciar el juego

    private void initGame() {
        snake.clear();
        snake.add(new Corner(WIDTH / 2, HEIGHT / 2));
        direction = Direction.LEFT;
        score = 0;
        gameOver = false;
        speed = DEFAULT_SPEED;
        spawnFood();
        clearCanvas();
        cnvGame.requestFocus();
    }
//Funcion para que el canva tenga el focus

    private void setupInputHandlers() {
        cnvGame.setFocusTraversable(true);
        cnvGame.setOnKeyPressed(this::handleKeyPress);
    }

    //Funcion para iniciar el bucle del juego
    private void startGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }

        gameLoop = new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    updateGame();
                    return;
                }
                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    updateGame();
                }
            }
        };
        gameLoop.start();
    }

    
    //Funcion para reconocer la tecla presionada
    private void handleKeyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                direction = Direction.UP;
                break;
            case A:
                direction = Direction.LEFT;
                break;
            case S:
                direction = Direction.DOWN;
                break;
            case D:
                direction = Direction.RIGHT;
                break;
        }
    }

    //Funcion para actualizar el juego 
    private void updateGame() {
        if (gameOver) {
            drawGameOver();
            return;
        }
        moveSnake();
        checkCollisions();
        clearCanvas();
        drawFood();
        drawSnake();
        drawScore();
    }
    //Funcion para mover la serpiente
    private void moveSnake() {
        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }
        switch (direction) {
            case UP:
                snake.get(0).y--;
                break;
            case DOWN:
                snake.get(0).y++;
                break;
            case LEFT:
                snake.get(0).x--;
                break;
            case RIGHT:
                snake.get(0).x++;
                break;
        }
    }
    //Funcion para revizar las colisiones
    private void checkCollisions() {
        if (snake.get(0).x < 0 || snake.get(0).x >= WIDTH
                || snake.get(0).y < 0 || snake.get(0).y >= HEIGHT) {
            gameOver = true;
        }
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
                break;
            }
        }
        if (snake.get(0).x == foodX && snake.get(0).y == foodY) {
            snake.add(new Corner(-1, -1));
            score++;
            spawnFood();
        }
    }
//Funcion para limpiar el canva
    private void clearCanvas() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * CORNER_SIZE, HEIGHT * CORNER_SIZE);
    }

    //Funcion para mostrar el fin del juego
    private void drawGameOver() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setFont(new Font("", 50));
        gc.fillText("Fin del Juego", 100, 175);
    }
    //Funcion para dibujar la comida 
    private void drawFood() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        Color foodColor = getFoodColor();
        gc.setFill(foodColor);
        gc.fillOval(foodX * CORNER_SIZE, foodY * CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
    }

    //Funcion para generar la comida en una posicion aleatoria
    private void spawnFood() {
        while (true) {
            foodX = random.nextInt(WIDTH);
            foodY = random.nextInt(HEIGHT);
            boolean foodOverlapsSnake = snake.stream().anyMatch(c -> c.x == foodX && c.y == foodY);
            if (!foodOverlapsSnake) {
                foodColorIndex = random.nextInt(5);
                speed++;
                break;
            }
        }
    }
//Funcion para elegir el color de la comida
    private Color getFoodColor() {
        return switch (foodColorIndex) {
            case 0 -> Color.PURPLE;
            case 1 -> Color.LIGHTBLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.PINK;
            case 4 -> Color.RED;
            default -> Color.WHITE;
        };
    }

    //Funcion para dibujar la serpiente
    private void drawSnake() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        Corner head = snake.get(0);
        double[] xPoints = new double[3];
        double[] yPoints = new double[3];
        switch (direction) {
            case UP:
                xPoints = new double[]{head.x * CORNER_SIZE, (head.x + 0.5) * CORNER_SIZE, (head.x + 1) * CORNER_SIZE};
                yPoints = new double[]{(head.y + 1) * CORNER_SIZE, head.y * CORNER_SIZE, (head.y + 1) * CORNER_SIZE};
                break;
            case DOWN:
                xPoints = new double[]{head.x * CORNER_SIZE, (head.x + 0.5) * CORNER_SIZE, (head.x + 1) * CORNER_SIZE};
                yPoints = new double[]{head.y * CORNER_SIZE, (head.y + 1) * CORNER_SIZE, head.y * CORNER_SIZE};
                break;
            case LEFT:
                xPoints = new double[]{(head.x + 1) * CORNER_SIZE, head.x * CORNER_SIZE, (head.x + 1) * CORNER_SIZE};
                yPoints = new double[]{head.y * CORNER_SIZE, (head.y + 0.5) * CORNER_SIZE, (head.y + 1) * CORNER_SIZE};
                break;
            case RIGHT:
                xPoints = new double[]{head.x * CORNER_SIZE, (head.x + 1) * CORNER_SIZE, head.x * CORNER_SIZE};
                yPoints = new double[]{head.y * CORNER_SIZE, (head.y + 0.5) * CORNER_SIZE, (head.y + 1) * CORNER_SIZE};
                break;
        }
        gc.fillPolygon(xPoints, yPoints, 3);
        gc.setStroke(Color.LIGHTGREEN);
        gc.setLineWidth(20);
        for (int i = 1; i < snake.size(); i++) {
            Corner c = snake.get(i);
            gc.strokeLine(c.x * CORNER_SIZE + CORNER_SIZE / 2, c.y * CORNER_SIZE + CORNER_SIZE / 2,
                    c.x * CORNER_SIZE + CORNER_SIZE / 2, c.y * CORNER_SIZE + CORNER_SIZE / 2);
        }
    }
    //Funcion para mostrar el puntaje
    private void drawScore() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 20));
        gc.fillText("Puntos: " + score, 10, 20);
    }
    // Funcion para liberar referencia al bucle de juego
    private void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
            gameLoop = null; 
        }
    }
}
