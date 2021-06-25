[ -f "/opt/${project.artifactId}/cron/${project.artifactId}.sh" ] && ln -sf "/opt/${project.artifactId}/cron/${project.artifactId}.sh" /etc/cron.daily/

systemctl daemon-reload
systemctl enable "${project.artifactId}"
