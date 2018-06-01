# Doing

## Write a line-of-sight function to reduce dungeon to only tiles that are visible to the entity's coordinates (only monsters in perception of player will be sent to the client)
### line-of-sight [area range yx]
1. **sight-yxs**: A vector of yxs that have a @range distance from @yx in @area (essentially the outlines of a circle)
2. **sight-line**: Draw a line (vector) from @yx to each of #sight-yxs, exiting prematurely only if it meets a :wall tile
  - fn: trace-path
3. All tiles *except* those that #sight-line traverses, will be replaced by the :dark tile

## A tick is done every time the player moves (use event-sourcing to compute the current state of the game)


# Later

## Replace data hash maps with records for performance and documentation improvement

## Review protocols and find a use for them (maybe in multimethods?)

## Handle case where unique-yx will enter an infinite loop if the dungeon map is too small (set a limit on max attempts and find out what to do when that limit is reached)

## Find a way to give an error if you provide a namespaced keyword that doesn't exist (as in the case of the :monter/spider typo) (should probably be defined in the gen-entity :default multimethod)


# Refactoring

## Place simplify-keyword into a util file that can be required by both cljs and clj code


# Assets

## For enemy sprites, rip from Dragon Slayer GB release
