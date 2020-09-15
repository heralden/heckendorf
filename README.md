# heckendorf

Procedurally generated roguelike dungeon crawler game implemented as a fullstack Clojure webapp.

Live at: http://heck.8620.cx

# Running locally

```
lein start
```

# Development

## Server

```
lein repl
heckendorf.web=> (-main)
```

Or `lein run` if you haven't been REPLightened yet.

## Client

```
lein figwheel dev
```

# Credits

Thanks to [Clint Bellanger](http://clintbellanger.net/) for the [tileset](https://opengameart.org/content/tileset-1bit-color). The tileset has been used with additions for extra monster sprites, one of them a modification to an existing sprite.
