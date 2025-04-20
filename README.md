Footprint
================
Footprint is a server-side Fabric mod to help keep your world file size manageable when players explore lots of terrain quickly, especially using Elytra.
Server admins often use (great) tools like [MCA Selector](https://github.com/Querz/mcaselector/) to prune chunks that have only been loaded for a very short time. Footprint simply makes this process automatic by not saving those chunks in the first place.
There may be reasons you want a chunk to be saved even though it wasn't loaded for long enough, the mod also has a few config options for that.
Footprint does NOT delete chunks that have already been saved, so it will never decrease your world size. You can still prune old chunks manually.


Thanks [Magistermark](https://github.com/magistermaks/fabric-simplelibs) for a very easy to use config library.


Features
---
* Load the server once with the mod installed to generate the config file (`footprint_config.properties` in your server's `config` folder). This config allows you to set conditions for when a chunk should be saved.
  * Every option is explained within the file itself.
  * Restart the server to apply changes.
* Options are:
  * Chunks are only saved after they've been loaded by players for a specified amount of ticks (default: 1200, which is 60 seconds). This uses the `inhabitedTime` value tracked by the vanilla game for local difficulty.
  * There are a bunch of actions you can turn on/off that will cause a chunk to be saved regardless of the `inhabitedTime`. Each of these can additionally specify a range of chunks around the event to also force save.
    * Block placed by player (default: on, range 0)
    * Block broken by player (default: on, range 0). Example: to prevent players from quickly grabbing a resource from the same chunk multiple times.
    * Entity hurt (default: off, because entities get hurt naturally quite often)
    * Item picked up by player (default: on, range 1)
    * Item dropped by player (default: on, range 1). Example: to avoid loss of items dropped while flying over new terrain.


Disclaimer
---
I'm a new modder. I needed this for a server I'm working on and made it as a learning opportunity. Therefore, it could be a little messy - use with caution. Make regular backups!! (This mod may even make that easier.)
There is a bit of a tradeoff here: chunk generation causes a lot of strain on the server, and chunks that aren't saved may need to be generated again later. So this mod is helpful if world size is your main issue, and not if you're having trouble with lag!
Feel free to use the code as you please, and improvement suggestions are welcome! (-:
