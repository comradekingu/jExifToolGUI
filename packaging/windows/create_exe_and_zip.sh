#!/bin/bash

# Script to make a windows 64bit executable linked to local V11 jre
# version 0.2, 2020-06-23, hvdw

# Requirements:
# launch4j from http://launch4j.sourceforge.net/
#     launch4j uses java v8, 9 or 10. Not 11 or above
# jre V11: download from https://adoptopenjdk.net/releases.html.
#      Untar it and rename resulting folder to jre (to keep it simple over the versions)

if [ "$1" = "" ]
then
        printf "\n\nYou have to provide the version\n\n"
        exit
fi

Version="$1.0"



# I normally put launch4j next to the jExifToolGUI folder
launch4jPATH="../../../launch4j/launch4j"
exe_zip_path="jExifToolGUI-$Version-win-x86_64"
#JRE="../../../java/JREs/windows-jre"

cp ../../jExifToolGUI.jar .

#Update the launch4j config file with the version
sed -e "s+VersionString+$Version+" jexiftoolgui-base.xml > jexiftoolgui.xml

# No jre included
$launch4jPATH ./jexiftoolgui.xml

rm -rf ${exe_zip_path}
mkdir ${exe_zip_path}
mv *.exe ${exe_zip_path}
cp ../../LICENSE ${exe_zip_path}
#cp -a "${JRE}" ${exe_zip_path}/jre

rm "${exe_zip_path}.zip"
zip -r9 "${exe_zip_path}.zip" "${exe_zip_path}"

rm -rf ${exe_zip_path}
