#! /bin/bash

CIRCLE_SHA1=$1

DOCKER_ORGANISATION=$2
DOCKER_REPOSITORY_NAME=$3

AWS_APPLICATION_NAME=$4
AWS_ENVIRONMENT_NAME=$5
AWS_EB_BUCKET_NAME=$6
AWS_REGION=$7

# Deploys image to Docker Hub
docker push $DOCKER_ORGANISATION/$DOCKER_REPOSITORY_NAME

# Creates a new Elastic Beanstalk version
DOCKERRUN_FILE=$CIRCLE_SHA1-Dockerrun.aws.json
sed "s/<TAG>/$CIRCLE_SHA1/" < Dockerrun.aws.json.template > $DOCKERRUN_FILE

# Copies the new versio to AWS S3
aws s3 cp $DOCKERRUN_FILE s3://$AWS_EB_BUCKET_NAME/$DOCKERRUN_FILE --region $AWS_REGION

# Creates an application version at Elastic Beanstalk
aws elasticbeanstalk create-application-version --application-name $AWS_APPLICATION_NAME \
  --version-label $CIRCLE_SHA1 --source-bundle S3Bucket=$AWS_EB_BUCKET_NAME,S3Key=$DOCKERRUN_FILE \
  --region $AWS_REGION

# Updates Elastic Beanstalk to the new version
aws elasticbeanstalk update-environment --environment-name $AWS_ENVIRONMENT_NAME \
    --version-label $CIRCLE_SHA1 --region $AWS_REGION
