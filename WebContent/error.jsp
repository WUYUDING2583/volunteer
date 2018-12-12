<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Struts Problem Report</title>
<style>
pre {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<h2>Struts Problem Report</h2>
	<p>Struts has detected an unhandled exception:</p>
	<div id="exception-info">
		<table>
			<tr>
				<td><strong>Messages</strong>:</td>
				<td></td>
			</tr>
			<tr>
				<td><strong>File</strong>:</td>
				<td>java/io/StringReader.java</td>
			</tr>
			<tr>
				<td><strong>Line number</strong>:</td>
				<td>50</td>
			</tr>
		</table>
	</div>
	<div id="stacktraces">
		<hr />
		<h3>Stacktraces</h3>
		<div class="stacktrace" style="padding-left: 0em">
			<strong>java.lang.NullPointerException</strong>
			<div>
				<pre> java.io.StringReader.&lt;init&gt;(StringReader.java:50) com.google.gson.JsonParser.parse(JsonParser.java:45) volunteer.action.ActivityAction.addActivity(ActivityAction.java:65) sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) java.lang.reflect.Method.invoke(Method.java:498) ognl.OgnlRuntime.invokeMethod(OgnlRuntime.java:897) ognl.OgnlRuntime.callAppropriateMethod(OgnlRuntime.java:1299) ognl.ObjectMethodAccessor.callMethod(ObjectMethodAccessor.java:68) com.opensymphony.xwork2.ognl.accessor.XWorkMethodAccessor.callMethodWithDebugInfo(XWorkMethodAccessor.java:117) com.opensymphony.xwork2.ognl.accessor.XWorkMethodAccessor.callMethod(XWorkMethodAccessor.java:108) ognl.OgnlRuntime.callMethod(OgnlRuntime.java:1375) ognl.ASTMethod.getValueBody(ASTMethod.java:91) ognl.SimpleNode.evaluateGetValueBody(SimpleNode.java:212) ognl.SimpleNode.getValue(SimpleNode.java:258) ognl.Ognl.getValue(Ognl.java:470) ognl.Ognl.getValue(Ognl.java:434) com.opensymphony.xwork2.ognl.OgnlUtil$3.execute(OgnlUtil.java:398) com.opensymphony.xwork2.ognl.OgnlUtil.compileAndExecuteMethod(OgnlUtil.java:450) com.opensymphony.xwork2.ognl.OgnlUtil.callMethod(OgnlUtil.java:396) com.opensymphony.xwork2.DefaultActionInvocation.invokeAction(DefaultActionInvocation.java:430) com.opensymphony.xwork2.DefaultActionInvocation.invokeActionOnly(DefaultActionInvocation.java:290) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:251) org.apache.struts2.interceptor.DeprecationInterceptor.intercept(DeprecationInterceptor.java:41) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) org.apache.struts2.interceptor.debugging.DebuggingInterceptor.intercept(DebuggingInterceptor.java:256) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.DefaultWorkflowInterceptor.doIntercept(DefaultWorkflowInterceptor.java:168) com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.validator.ValidationInterceptor.doIntercept(ValidationInterceptor.java:265) org.apache.struts2.interceptor.validation.AnnotationValidationInterceptor.doIntercept(AnnotationValidationInterceptor.java:76) com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.ConversionErrorInterceptor.intercept(ConversionErrorInterceptor.java:138) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:229) com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:229) com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.StaticParametersInterceptor.intercept(StaticParametersInterceptor.java:191) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) org.apache.struts2.interceptor.MultiselectInterceptor.intercept(MultiselectInterceptor.java:73) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) org.apache.struts2.interceptor.DateTextFieldInterceptor.intercept(DateTextFieldInterceptor.java:125) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) org.apache.struts2.interceptor.CheckboxInterceptor.intercept(CheckboxInterceptor.java:91) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) org.apache.struts2.interceptor.FileUploadInterceptor.intercept(FileUploadInterceptor.java:253) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor.intercept(ModelDrivenInterceptor.java:100) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.ScopedModelDrivenInterceptor.intercept(ScopedModelDrivenInterceptor.java:141) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.ChainingInterceptor.intercept(ChainingInterceptor.java:145) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.PrepareInterceptor.doIntercept(PrepareInterceptor.java:171) com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.I18nInterceptor.intercept(I18nInterceptor.java:140) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) org.apache.struts2.interceptor.ServletConfigInterceptor.intercept(ServletConfigInterceptor.java:164) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.AliasInterceptor.intercept(AliasInterceptor.java:193) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor.intercept(ExceptionMappingInterceptor.java:189) com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:245) org.apache.struts2.impl.StrutsActionProxy.execute(StrutsActionProxy.java:54) org.apache.struts2.dispatcher.Dispatcher.serviceAction(Dispatcher.java:575) org.apache.struts2.dispatcher.ng.ExecuteOperations.executeAction(ExecuteOperations.java:81) org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter.doFilter(StrutsPrepareAndExecuteFilter.java:99) org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:493) org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81) org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:650) org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342) org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:800) org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:806) org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1498) org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) java.lang.Thread.run(Thread.java:748) </pre>
			</div>
		</div>
	</div>
	<div class="footer">
		<hr />
		<p>You are seeing this page because development mode is enabled.
			Development mode, or devMode, enables extra debugging behaviors and
			reports to assist developers. To disable this mode, set:
		<pre> struts.devMode=false </pre>
		in your
		<code>WEB-INF/classes/struts.properties</code>
		file.
		</p>
	</div>
</body>
</html>