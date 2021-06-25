systemctl stop "${project.artifactId}" > /dev/null 2>&1 || :

[ ! "$(getent passwd ${project.artifactId})" ] && useradd -d "/opt/${project.artifactId}" -s /sbin/nologin "${project.artifactId}"
[ ! "$(getent group ${project.artifactId})" ] && groupadd "${project.artifactId}"
usermod -a -G "${project.artifactId}" -s /sbin/nologin -d "/opt/${project.artifactId}" "${project.artifactId}"
