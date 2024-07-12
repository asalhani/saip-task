# saip-task
This is the task givien by SAIP during after interview

- Java 17


- In BPMN, for service task we have 2 options:
	1- Java Class 	 => then we have to provide full package name and class name
	2- Java Delegate => Java class should be added as bean (by using @Component annotation). DelegateExpression should be set to as #{bean_naem}
In both cases, you have to create a class and implement JavaDelegate interface.

