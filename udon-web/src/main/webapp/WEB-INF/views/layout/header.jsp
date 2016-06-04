<div style="width: 100%; max-width: 100%;">
    <h1>
        <a href="${pageContext.request.contextPath}/">u~don</a>
    </h1>
    <sec:authorize access="isAuthenticated()">
        <form:form method="POST"
            action="${pageContext.request.contextPath}/logout"
            style="float:right;">
            <button type="submit" name="logout" class="btn">
                <span class="medium">Logout</span>
            </button>
        </form:form>
    </sec:authorize>
</div>