CleanSponge
===========

A cleanroom minecraft server implementation implementing the [Sponge][Sponge] API ([SpongeApi Github][SpongeGitHub]).

Features:
- Uses a parallel jobs system allowing world ticks and other actions to happen in parallel using a lock-less non-blocking model.
- Designed for performance.
- Contains absolutely no proprietary mojang code.

Goal Features
- Highly extensible for modding.
- Having a better project name.
- Having more hours in the day to actually complete this.

Compilation
-----------

We use gradle to handle our dependencies.

- Check out this repository.
- Run ```./gradlew build```

[Sponge]: https://www.spongepowered.org/
[SpongeGitHub]: https://github.com/spongepowered/SpongeApi.git
[Gradle]: https://gradle.org/
