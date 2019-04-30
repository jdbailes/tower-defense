# 🛡 tower-defense 🛡
_Current working branch is **theHack**_

- [x] Create an enemy & move across the map
- [x] Create a wave of enemies that spawn & move across the map
- [x] Create a tower (spaceship)
- [x] Add collission circles to enemies and tower to prep for bullets (We need to know when enemies are in range etc)
- [x] Allow the tower to shoot at an enemy when the enemy is in range of the tower
- [ ] Add map/background using tiled map editor
- [ ] Improve the missile-to-enemy implementation (it's a bit buggy at the moment)

## Initial Setup
IntelliJ would be the recomended IDE for this project, rather than Eclipse - but if you're familier with Eclipse then that's fine. Any instructions in this README will be targetted at an IntelliJ setup.

When you run Desktop Project for the first time the application will fail. To fix this, open the Run Configuration and set the working directory to the **core/assets** directory!

## Git Strategy
**Master** should represent our most recent release/submission, and any further development between submissions should be done on the **dev** branch. This strategy will allow us to maintain a view of our most recent release whilst allowing us to progress forward with further development on a more unstable dev branch. 

On the run up to a submission, we can orchestrate a group pull request/code review where we merge the dev branch into the master branch - this will give everyone an oppurtunity to see the code before a submission.

## Useful Links
[LibGDX documentation](https://libgdx.badlogicgames.com/documentation/)

[LibGDX API docs](https://libgdx.badlogicgames.com/ci/nightlies/docs/api/overview-summary.html)

[GamesFromScratch LibGDX tutorial](https://www.gamefromscratch.com/page/LibGDX-Tutorial-series.aspx)

[A Simple Game in LibGDX](https://github.com/libgdx/libgdx/wiki/A-simple-game)

[Tiled Map Editor](https://www.mapeditor.org/)
