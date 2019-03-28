# Known bugs
 
- If you gain enough XP in one tick to level up multiple times, bad things will happen

- Handle case where unique-yx will enter an infinite loop if the dungeon map is too small (set a limit on max attempts and find out what to do when that limit is reached)

# Improvements

- Refactor, add docstrings, and encapsulate (into different files?) the functionality in dungeon.clj. Also please write tests.

- Replace data hash maps with records for performance and documentation improvement

- Review protocols and find a use for them (maybe in multimethods?)

- Find a way to give an error if you provide a namespaced keyword that doesn't exist (as in the case of the :monter/spider typo) (should probably be defined in the gen-entity :default multimethod)

# Refactoring

- Pass `entities` to `cljs-roguelike.action` functions instead of the entire game object, if it's possible to do this once `dispatch` methods have been fleshed out.
