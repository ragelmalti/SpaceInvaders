package invaders.configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import invaders.configuration.BunkerConfig;
import invaders.configuration.EnemyConfig;
import invaders.configuration.GameConfig;
import invaders.configuration.PlayerConfig;

public class ConfigReader {
	/**
	 * You will probably not want to use a static method/class for this.
	 * 
	 * This is just an example of how to access different parts of the json
	 * 
	 * @param path The path of the json file to read
	 */
	
	private GameConfig game;
	private PlayerConfig player;
	private List<EnemyConfig> enemies = new ArrayList<>();
	private List<BunkerConfig> bunkers = new ArrayList<>();
	
	public ConfigReader(String path) {
		// Upon initialisation of the ConfigReader object, it will read the config.json file,
		// and create configuration objects, for the game, player, enemies, and the bunker.
		// If there is an error, all the objects will be initialised to null.
		
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// *** READING THE GAME SECTION ***
			JSONObject jsonGame = (JSONObject) jsonObject.get("Game");
			int gameWidth = (int)(long) ((JSONObject) jsonGame.get("size")).get("x");
			int gameHeight = (int)(long) ((JSONObject) jsonGame.get("size")).get("y");

			this.game = new GameConfig(gameWidth, gameHeight);

			// *** READING THE PLAYER SECTION ***
			JSONObject jsonPlayer = (JSONObject) jsonObject.get("Player");
			String playerColour = (String) jsonPlayer.get("colour");
			int playerSpeed = (int)(long) jsonPlayer.get("speed");
			int playerLives = (int)(long) jsonPlayer.get("lives");
			Double playerPosX = (double)(long) ((JSONObject) jsonPlayer.get("position")).get("x");
			Double playerPosY = (double)(long) ((JSONObject) jsonPlayer.get("position")).get("y");

			this.player = new PlayerConfig(playerColour, playerSpeed, playerLives, playerPosX, playerPosY);

			// *** READING ENEMY SECTION ***
			JSONArray jsonEnemies = (JSONArray) jsonObject.get("Enemies");

			for (Object obj : jsonEnemies) {
				JSONObject jsonEnemy = (JSONObject) obj;
				Double enemyPositionX = (double)(long)((JSONObject) jsonEnemy.get("position")).get("x");
				Double enemyPositionY = (double)(long)((JSONObject) jsonEnemy.get("position")).get("y");
				String enemyProjectileStrategy = (String) jsonEnemy.get("projectile");

				this.enemies.add(new EnemyConfig(enemyPositionX, enemyPositionY, enemyProjectileStrategy));
			}

			// *** READING BUNKER SECTION ***
			JSONArray jsonBunkers = (JSONArray) jsonObject.get("Bunkers");

			for (Object obj: jsonBunkers) {
				JSONObject jsonBunker = (JSONObject) obj;
				Double bunkerPositionX = (double)(long)((JSONObject) jsonBunker.get("position")).get("x");
				Double bunkerPositionY = (double)(long)((JSONObject) jsonBunker.get("position")).get("y");
				Double bunkerSizeX = (double)(long)((JSONObject) jsonBunker.get("size")).get("x");
				Double bunkerSizeY = (double)(long)((JSONObject) jsonBunker.get("size")).get("y");

				this.bunkers.add(new BunkerConfig(bunkerPositionX, bunkerPositionY, bunkerSizeX, bunkerSizeY));
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: no configuration file found at 'src/main/resources/config.json'");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public GameConfig getGameConfig() {
		return this.game;
	}
	
	public PlayerConfig getPlayerConfig() {
		return this.player;
	}

	public List<EnemyConfig> getEnemyConfig() {
		return this.enemies;
	}

	public List<BunkerConfig> getBunkerConfig() {
		return this.bunkers;
	}
}
