<#include "pageMacro.ftl">  
<@page>
<body>
	<div class="container">
		<div class="header"><h2>Ciki - Chu Lung's wiki</h2>
		<div>
		<div class="ciki">
			<#list cikis as ciki>
				<div class="ciki-item">
					<div class="ciki-name">
						<h2 id="${ciki.id}" >${ciki.cikiName}</h2>
					<div>
					<div class="ciki-list">
						<ul>
							<#assign titelName=ciki.titelName>
							<#list ciki.cikis as childciki>
							<li>
								<a href="https://ciki.chulung.com/${titelName}/${childciki.titelName}.html">${childciki.titelName}</a>
							</li>
							</#list>
						</ul>
					<div>
				<div>
			</#list>
		<div>
	<div>
</body>
</@page>
