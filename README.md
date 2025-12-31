# JRuby dependencies

This project just contains a simple [](project.clj) file for use in packaging
up an uberjar which has just JRuby-specific dependencies that
[OpenVox Server](https://github.com/openvoxproject/openvox-server) needs.  The
uberjar specifically excludes some dependencies that OpenVox Server already
uses, like `joda-time` and `org.yaml/snakeyaml`, in order to allow OpenVox
Server to provide its own preferred versions.  This project allows OpenVox
Server's "release" uberjar (which excludes JRuby-specific dependencies) to be
run in combination with the JRuby dependencies uberjar on the same Java
classpath.  This allows the specific version of the JRuby dependencies uberjar
to be swapped out at run-time, e.g., for switching between the use of a
JRuby 1.7 vs. a JRuby 9k dependencies jar.
