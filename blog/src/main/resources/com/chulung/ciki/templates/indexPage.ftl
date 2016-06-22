<#include "pageMacro.ftl">  
<@page>
<body>
	<div class="container">
		<div class="header"><h2>Ciki - Chu Lung's wiki</h2>
		<div>
		<div class="category">
			<#list categories as category>
				<div class="category-item">
					<div class="category-name">
						<h2 id="${category.id}" >${category.categoryName}</h2>
					<div>
					<div class="category-list">
						<ul>
							<#assign categoryName=category.categoryName>
							<#list category.categories as childCategory>
							<li>
								<a href="https://ciki.chulung.com/${categoryName}/${childCategory.categoryName}.html">${childCategory.categoryName}</a>
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
