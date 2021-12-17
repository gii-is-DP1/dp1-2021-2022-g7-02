<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
description="Name of the active menu: home, owners, vets or error"%>


<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<spring:url value=" /" htmlEscape="true" />"></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-navbar">
				<span class="sr-only">
					<os-p>Toggle navigation</os-p>
				</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/" title="Home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span></span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'owners'}" url="/games" title="Find games">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Find games</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'vets'}" url="/games/current" title="Current game">
					<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
					<span>Current game</span>
				</petclinic:menuItem>

				<sec:authorize access='hasAuthority("admin")'>
					<petclinic:menuItem active="${name eq 'vets'}" url="/users" title="Users">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Users</span>
					</petclinic:menuItem>


					<petclinic:menuItem active="${name eq 'vets'}" url="" title="cards" dropdown="${true}">
						<ul class="dropdown-menu">
							<li>
								<div class="row">
									<div class="text-center">
										<a href="<c:url value=" /cards/heroes" />">Heroes</a>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="row">
									<div class="text-center">
										<a href="<c:url value=" /cards/scenes" />">Scenes</a>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="row">
									<div class="text-center">
										<a href="<c:url value=" /cards/skills" />">Hero Skill</a>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="row">
									<div class="text-center">
										<a href="<c:url value=" /cards/enemies" />">Enemies</a>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="row">
									<div class="text-center">
										<a href="<c:url value=" /cards/market" />">Market cards</a>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="row">
									<div class="text-center">
										<a href="<c:url value=" /cards/tokens/tokensCardsListing.jsp" />">Tokens</a>
									</div>
								</div>
							</li>
						</ul>
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Cards</span>
					</petclinic:menuItem>
				

					<petclinic:menuItem active="${name eq 'error'}" url="/oups"
						title="trigger a RuntimeException to see how it is handled">
						<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
						<span>Error</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<petclinic:menuItem active="${name eq 'vets'}" url="/achievements" title="Achievements">
					<span class="glyphicon glyphicon-glass" aria-hidden="true"></span>
					<span></span>
				</petclinic:menuItem>
			</ul>



			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value=" /login" />">Login</a></li>
					<li><a href="<c:url value=" /users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span
								class="glyphicon glyphicon-user"></span>
							<strong>
								<sec:authentication property="name" />
							</strong> <span class="glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>

							<li>
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="/users/profile" class="btn btn-primary btn-block">My
													Profile</a>
												<a href="/users/profile/edit" class="btn btn-success btn-block">Edit
													profile</a>
												<a href="/logout" class="btn btn-danger btn-block">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>

						</ul>
					</li>
				</sec:authorize>
			</ul>
		</div>


	</div>
</nav>