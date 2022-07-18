package com.cloven.dev;


import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
// import software.amazon.awscdk.services.sqs.Queue;
// import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.services.ec2.CloudFormationInit;
import software.amazon.awscdk.services.ec2.ExecuteFileOptions;
import software.amazon.awscdk.services.ec2.IMachineImage;
import software.amazon.awscdk.services.ec2.InitCommand;
import software.amazon.awscdk.services.ec2.Instance;
import software.amazon.awscdk.services.ec2.InstanceType;
import software.amazon.awscdk.services.ec2.MachineImage;
import software.amazon.awscdk.services.ec2.OperatingSystemType;
import software.amazon.awscdk.services.ec2.Peer;
import software.amazon.awscdk.services.ec2.Port;
import software.amazon.awscdk.services.ec2.SecurityGroup;
import software.amazon.awscdk.services.ec2.SecurityGroupProps;
import software.amazon.awscdk.services.ec2.SsmParameterImageOptions;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.UserData;
import software.amazon.awscdk.services.ec2.Vpc;

public class ErpNewStackStack extends Stack {
    public ErpNewStackStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public ErpNewStackStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        final Vpc vpc = Vpc.Builder.create(this, "erpdev").subnetConfiguration(Vpc.DEFAULT_SUBNETS_NO_NAT).build();
		final SecurityGroupProps securityGroupProps = SecurityGroupProps.builder().vpc(vpc).allowAllOutbound(true)
				.build();
		final UserData userData = UserData.forLinux();
		userData.addCommands("yum install -y httpd");
		userData.addExecuteFileCommand(
				ExecuteFileOptions.builder().filePath("D:\\GSPANN\\erp-dev-stack-cdk\\user-data.sh").build());
		;
		// userData.forLinux(LinuxUserDataOptions.builder().shebang(id))
		final SecurityGroup securityGroup = new SecurityGroup(this, "securityGroup", securityGroupProps);
		securityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(80), "httpIpv4");
		securityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(8080), "httpIpv4");
		securityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(22), "httpIpv4");
		securityGroup.addIngressRule(Peer.anyIpv6(), Port.tcp(22), "httpIpv6");
		securityGroup.addIngressRule(Peer.anyIpv6(), Port.tcp(80), "httpIpv6");

		IMachineImage machineImage = MachineImage.fromSsmParameter(
				"/aws/service/canonical/ubuntu/server/focal/stable/current/amd64/hvm/ebs-gp2/ami-id",
				SsmParameterImageOptions.builder().os(OperatingSystemType.LINUX).userData(userData).build());
		CloudFormationInit.fromElements(InitCommand.shellCommand("sudo apt-get install -y nginx"));
		Instance instance = Instance.Builder.create(this, "EC2 from CDK").instanceType(new InstanceType("t2.micro"))
				.machineImage(machineImage).securityGroup(securityGroup).vpc(vpc).keyName("erpdev")
				.vpcSubnets(SubnetSelection.builder().subnetType(SubnetType.PUBLIC).build()).build();

    }
}
