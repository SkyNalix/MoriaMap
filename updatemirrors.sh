#!/bin/bash
rm -rf moriamap.git
git clone --mirror https://gaufre.informatique.univ-paris-diderot.fr/tazouev/moriamap.git
cd moriamap.git
git remote set-url --push origin https://github.com/SkyNalix/MoriaMap.git
git fetch -p origin
git push --mirror
cd ..
rm -rf moriamap.git
