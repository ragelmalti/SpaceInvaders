# Space Invaders
## How to run the game
Simply type `gradle run` in a terminal prompt, in the current working directory of the build.gradle file. Make sure there is a valid config file in the `src/resources/` folder, otherwise the game will crash, and refuse to boot. 

## Design Patterns
### Factory method
- Utilised by the Projectile class, ProjectileCreator interface and the ConcreteProjectileCreator class.
- Code for the factory method is in `src/main/java/invaders/entities/factory`
### Builder Pattern
- Utilised by the Bunker and Enemy classes, EBuilderInterface and BBuilderInterface interfaces and BunkerBuilder and EnemyBuilder classes.
- Code for the builder pattern is in `src/main/java/invaders/entities/builder`

### State Pattern
- Utilised by the Bunker class, BunkerState interface, GreenState, YellowState and RedState classes.
- Code for the state pattern is in `src/main/java/invaders/entities/state`

## Strategy Pattern
- Utilised by the Projectile class, ProjectileStrategy interface, FastStraightProjectile, SlowStraightProjectile and PlayerProjectile classes.
- Code for the state pattern is in `src/main/java/invaders/entities/strategy`
