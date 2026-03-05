# Java Annotation Custom

Este projeto tem como objetivo demonstrar a criação e o uso de Anotações (@Annotations) em Java. As anotações são um recurso poderoso da linguagem Java que permite adicionar metadados ao código, os quais podem ser processados em tempo de compilação, execução ou por ferramentas externas.

* Criar anotações personalizadas em Java.
* Aplicar anotações em classes, métodos e campos.
* Processar as anotações em tempo de execução utilizando reflexão (Reflection).
* Explorar exemplos práticos de como as anotações podem ser utilizadas para validar, configurar e otimizar o código.

## Docker

	docker build -t java-annotation-custom .
	
	docker run --rm --name java-annotation-custom java-annotation-custom

	
## Local

Voce precisa chamar seu Weaver para conseguir ver o Aspect, se nao ele passa invisivel

	java -javaagent:/home/username/.m2/repository/org/aspectj/aspectjweaver/1.9.25.1/aspectjweaver-1.9.25.1.jar -cp target/classes com.pro.App

### Saida Output

	java -javaagent:/home/username/.m2/repository/org/aspectj/aspectjweaver/1.9.25.1/aspectjweaver-1.9.25.1.jar -cp target/classes com.pro.App
	WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
	WARNING: sun.misc.Unsafe::objectFieldOffset has been called by org.aspectj.weaver.loadtime.ClassLoaderWeavingAdaptor (file:/home/username/.m2/repository/org/aspectj/aspectjweaver/1.9.25.1/aspectjweaver-1.9.25.1.jar)
	WARNING: Please consider reporting this to the maintainers of class org.aspectj.weaver.loadtime.ClassLoaderWeavingAdaptor
	WARNING: sun.misc.Unsafe::objectFieldOffset will be removed in a future release
	[AppClassLoader@14dad5dc] info AspectJ Weaver Version 1.9.25.1 built on Tuesday Dec 16, 2025 at 15:21:40 PST
	[AppClassLoader@14dad5dc] info register classloader jdk.internal.loader.ClassLoaders$AppClassLoader@14dad5dc
	[AppClassLoader@14dad5dc] info using configuration /home/username/workspace/java-annotation-custom/target/classes/META-INF/aop.xml
	[AppClassLoader@14dad5dc] info register aspect com.pro.aspect.LogAspect
	[AppClassLoader@14dad5dc] weaveinfo at com/pro/App.java:7::0 Join point 'method-call(void com.pro.Init.add())' in Type 'com.pro.App' (App.java:7) advised by before advice from 'com.pro.aspect.LogAspect' (LogAspect.java)
		see also: com/pro/aspect/LogAspect.java::0
	[AppClassLoader@14dad5dc] weaveinfo at com/pro/Init.java:12::0 Join point 'method-execution(void com.pro.Init.add())' in Type 'com.pro.Init' (Init.java:12) advised by before advice from 'com.pro.aspect.LogAspect' (LogAspect.java)
		see also: com/pro/aspect/LogAspect.java::0
	Aspect acionado: Teste AspectJ
	Aspect acionado: Teste AspectJ
	----- Java Annotation Custom | Main -----

### Explicação

**O log do AspectJ**

weaveinfo at com/pro/App.java:7::0 Join point 'method-call(void com.pro.Init.add())' in Type 'com.pro.App' (App.java:7) advised by before advice from 'com.pro.aspect.LogAspect' (LogAspect.java::0)
see also: com/pro/aspect/LogAspect.java::0

Isso significa que o AspectJ aplicou o advice na chamada do método add() dentro de App. O join point é o ponto onde você chama init.add(). Também mostra que aplicou na execução do método dentro de Init.java.

**Saída do programa**

Aspect acionado: Teste AspectJ

Aspect acionado: Teste AspectJ

----- Java Annotation Custom | Main -----

Dois disparos do aspect:

Antes da execução do método Init.add()
(join point method-execution)

Antes da chamada do método
(join point method-call dentro de App)

Depois, aparece o System.out.println do método add(). Isso é completamente normal com LTW, especialmente quando você tem method-call + method-execution.

**Por que aparecem dois disparos?**

Você tem @Before("@annotation(logAnnotation)"):

AspectJ, em LTW, pode interceptar tanto:

A execução do método (method-execution). A chamada do método (method-call). Por isso vemos o aspecto disparando duas vezes no console.
