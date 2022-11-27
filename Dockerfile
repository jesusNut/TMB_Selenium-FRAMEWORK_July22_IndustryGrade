# Before using this Dockerfile, refer - Dockerfile_old
# Image created from this Dockerfile is available in Dockerhub @ https://hub.docker.com/r/jesusnut/seleniumindustrygradeframework_tmb

FROM java11-maven3.6.3-all_dependencies
ENTRYPOINT ["/bin/bash","./executeTests.sh"]
