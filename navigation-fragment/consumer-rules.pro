# Keep the fully-qualified class name (FQCN) of the classes that extends Fragment
# It's required when your project uses the fragmentClassName parameter of the
# NavGraphBuilder.fragment extension function from the :navigation-fragment artifact.
# Note that it's NOT included by default to avoid unnecesarry keeping the class.
-keep class * extends androidx.fragment.app.Fragment { *; }
