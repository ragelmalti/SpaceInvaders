package invaders.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import invaders.GameObject;
import invaders.App;
import invaders.configuration.ConfigReader;
import invaders.configuration.BunkerConfig;
import invaders.configuration.EnemyConfig;
import invaders.entities.Player;
import invaders.entities.builder.BunkerBuilder;
import invaders.entities.builder.EnemyBuilder;
import invaders.entities.state.*;
import invaders.entities.Bunker;
import invaders.entities.Enemy;
import invaders.entities.EnemyMove;
import invaders.entities.Projectile;
import invaders.entities.strategy.*;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import invaders.entities.factory.*;
//import invaders

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private ConfigReader config = App.getConfig();
	private Player player;
	private List<Enemy> enemies;
	private List<Bunker> bunkers;
	private List<Collider> colliders;
	private Projectile playerProjectile;
	private List<Projectile> enemyProjectiles; // Set to size 3.
	private EnemyMove move = EnemyMove.RIGHT;
	private boolean enemyDirectionFlip = true;
	private double enemyPosition;
	private double enemySpeed = 0.2;
	private boolean GameOver = false;

	private boolean left;
	private boolean right;

	public GameEngine(){ //Removed String config argument, because I thought it was redundant.
		// read the config here
		// Ask what the difference between gameobjects and renderables is.
		gameobjects = new ArrayList<GameObject>();
		renderables = new ArrayList<Renderable>();
		colliders = new ArrayList<Collider>();
		enemies = new ArrayList<Enemy>();
		enemyProjectiles = new ArrayList<Projectile>();
		bunkers = new ArrayList<Bunker>();
		player = new Player(new Vector2D(config.getPlayerConfig().getPositionX(), config.getPlayerConfig().getPositionY()));
		
		// Add Bunkers too the game lol
		for (BunkerConfig bunk : config.getBunkerConfig()) {
			BunkerBuilder bBuilder = new BunkerBuilder();
			Vector2D position = new Vector2D(bunk.getPositionX(), bunk.getPositionY());
			bBuilder.setPosition(position);
			bBuilder.setWidth(bunk.getSizeX());
			bBuilder.setHeight(bunk.getSizeY());
			bBuilder.setState(new GreenState());
			Bunker bunker = bBuilder.finaliseBuild();
			bunkers.add(bunker);
			renderables.add(bunker);
		}
		// Add enemies now
		for (EnemyConfig enemyConfig : config.getEnemyConfig()) {
			EnemyBuilder eBuilder = new EnemyBuilder();
			Vector2D position = new Vector2D(enemyConfig.getPositionX(), enemyConfig.getPositionY());
			// Write code to detect strategy for each enemy.
			if(enemyConfig.getProjectile().equals("slow_straight")) {
				eBuilder.setStrategy(new SlowStraightProjectile());
			}
			else if(enemyConfig.getProjectile().equals("fast_straight")) {
				eBuilder.setStrategy(new FastStraightProjectile());
			}
			else {
				// If no other value found, Slow Straight strat will be set as a default.
				eBuilder.setStrategy(new SlowStraightProjectile());
			}

			eBuilder.setPosition(position);
			Enemy enemy = eBuilder.finaliseBuild();
			enemies.add(enemy);
			renderables.add(enemy);
		}
		//bunker = new Bunker(new Vector2D(0, 0));
		renderables.add(player);
		//renderables.add(bunker);
		for(Renderable renderable: renderables) {
			if(renderable instanceof Collider) {
				colliders.add((Collider) renderable);
			}
		}
	}

	/**
	 * Updates the game/simulation
	 */
	public void update() {
		movePlayer();
		// If the player is dead, then all objects are removed from the game.
		// Esentially, the game over screen.
		if(!this.player.isAlive()) {
			this.GameOver = true;
		}

		if(GameOver) {
			renderables.forEach((r) -> {r.markForDeletion();});
		}
		
		for(GameObject go: gameobjects){
			go.update();
		}

		for(Renderable renderable: renderables) {
			if(renderable.getDeletion()) {
				colliders.remove((Collider) renderable);
			}
		}

		for(Collider c1: colliders) {
			for(Collider c2: colliders) {
				if (c1 == c2) {
					continue;
				}
				if(c1.isColliding(c2)) {
					//System.out.println("Collision!");
					c1.handleCollision(c2);
					c2.handleCollision(c1);
				}
			}
		}

		bunkers.removeIf(Bunker::getDeletion);

		// Now we handle the other classes :O
		for(Projectile p : enemyProjectiles) {
			p.move();
			if(p.getPosition().getY() >= App.getScreenHeight() - 30) {
				p.markForDeletion();
			}
		}
		enemyProjectiles.removeIf(Projectile::getDeletion);
		
		for(Enemy e : enemies) {
			// Alien shooting code
			if(enemyProjectiles.size() < 3) {
				Random rand = new Random();
        		int randomValue = rand.nextInt() % 100;
				if(randomValue == 1) {
					this.enemyShoot(e);
				}
			}
			
			// Alien moving code
			if(move == EnemyMove.RIGHT) {
				e.right(this.enemySpeed);
				if(e.getPosition().getX() >= App.getScreenWidth() - 50) {
					move = EnemyMove.DOWN;
					enemyPosition = enemies.get(0).getPosition().getY() + 15;
				}
			}
			else if(move == EnemyMove.DOWN) {
				e.down(this.enemySpeed);
				if(enemies.get(0).getPosition().getY() >= enemyPosition) {
					if(enemyDirectionFlip) {
						move = EnemyMove.LEFT;
						enemyDirectionFlip = false;
					}
					else if(!enemyDirectionFlip) {
						move = EnemyMove.RIGHT;
						enemyDirectionFlip = true;
					}
					
				}
			}
			else if(move == EnemyMove.LEFT) {
				e.left(this.enemySpeed);
				if(e.getPosition().getX() <= 0) {
					move = EnemyMove.DOWN;
					enemyPosition = enemies.get(0).getPosition().getY() + 15;
				}
			}
			// If an enemy reaches the bottom of the screen, then a game over is triggered.
			if(e.getPosition().getY() >= App.getScreenHeight() - 30) {
				this.GameOver = true;
			}
			if(e.getDeletion()) {
				// Every time an enemy is killed, we want to gradually increase the speed.
				this.enemySpeed = enemySpeed + 0.1;
			}
		}

		enemies.removeIf(Enemy::getDeletion);
		
		if(playerProjectile != null) {
			playerProjectile.move();
			if(playerProjectile.getDeletion()) {
				this.playerProjectile.getPosition().setX(0);
				this.playerProjectile.getPosition().setY(0);
				this.playerProjectile = null;
			}
			//System.out.println(playerProjectile.getPosition().getY());
			
			// Checks if projectile is at the top of the screen.
			// If it is, then it removes it.	
			else if(playerProjectile.getPosition().getY() <= 0) {
				this.playerProjectile.markForDeletion();
				this.playerProjectile = null;
			}
		}

		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables){
			if(!ro.getLayer().equals(Renderable.Layer.FOREGROUND)){
				continue;
			}
			if(ro.getPosition().getX() + ro.getWidth() >= App.getScreenWidth()) {
				ro.getPosition().setX((App.getScreenWidth() - 1)-ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if(ro.getPosition().getY() + ro.getHeight() >= App.getScreenHeight()) {
				ro.getPosition().setY((App.getScreenHeight() - 1)-ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);
			}
		}
	}

	public List<Renderable> getRenderables(){
		return renderables;
	}


	public void leftReleased() {
		this.left = false;
	}

	public void rightReleased(){
		this.right = false;
	}

	public void leftPressed() {
		this.left = true;
	}
	public void rightPressed(){
		this.right = true;
	}

	public boolean shootPressed(){
		player.shoot();
		ProjectileCreator projCreator = new ConcreateProjectileCreator();
		if(this.playerProjectile == null) {
			Projectile bullet = projCreator.createProjectile(new Vector2D(player.getPosition().getX() + 7, player.getPosition().getY() - 35), new PlayerProjectile());
			this.playerProjectile = bullet;
			renderables.add(playerProjectile);
			colliders.add((Collider) this.playerProjectile);
		}
		return true;
	}

	public void enemyShoot(Enemy enemy) {
		Random rand = new Random();
        int randomValue = rand.nextInt() % 2;
		ProjectileStrategy strategy = enemy.getStrategy();

		ProjectileCreator projCreator = new ConcreateProjectileCreator();
		Projectile bullet = projCreator.createProjectile(new Vector2D(enemy.getPosition().getX() + 7, enemy.getPosition().getY() + 30), strategy);
		enemyProjectiles.add(bullet);
		renderables.add(bullet);
		colliders.add((Collider) bullet);
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}
	}
}
