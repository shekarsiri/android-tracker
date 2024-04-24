# Check if a version argument was provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <version>"
    exit 1
fi

version=$1

# Adding all changes to git
git add -A

# Committing changes
git commit -m "build change"

# Tagging the commit
git tag -a "$version" -m "Release"

# Pushing the tag
git push shekar "$version"