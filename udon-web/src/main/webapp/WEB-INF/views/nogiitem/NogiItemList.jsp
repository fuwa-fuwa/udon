<div id="wrapper">
    <h3>NogiItems</h3>
    <hr>
    <form:form action="${pageContext.request.contextPath}/nogiitem/add"
        method="POST" modelAttribute="nogiItemForm">
            name:
            <form:input path="name" size="30" />
        <form:errors path="name" style="color:red" />
            / count:
            <form:input path="count" size="5" />
        <form:errors path="count" style="color:red" />
        <form:button name="add" class="btn">
            <span class="small">add</span>
        </form:button>
    </form:form>
    <hr>
    <table>
        <tr>
            <th>name</th>
            <th>cnt</th>
            <th>op</th>
        </tr>
        <c:forEach items="${nogiItems}" var="nogiItem">
            <tr id="itemRow${f:h(nogiItem.id)}">
                <td>${f:h(nogiItem.name)}</td>
                <td>
                    <span id="itemCount${f:h(nogiItem.id)}">${f:h(nogiItem.count)}</span>
                </td>
                <td>
                    <a href="javascript:void(0)" class="btn-icon"
                        onclick="javascript:countItem(${f:h(nogiItem.id)},'plus','itemCount${f:h(nogiItem.id)}');return false;">+</a>
                    <a href="javascript:void(0)" class="btn-icon"
                        onclick="javascript:countItem(${f:h(nogiItem.id)},'minus','itemCount${f:h(nogiItem.id)}');return false;">-</a>
                    <a href="javascript:void(0)" class="btn-icon"
                        onclick="javascript:deleteItem(${f:h(nogiItem.id)},'itemRow${f:h(nogiItem.id)}');return false;">x</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<sec:csrfMetaTags />
<script
    src="${pageContext.request.contextPath}/resources/app/js/jquery-1.12.4.min.js">
	
</script>
<script>
	var contextPath = $("meta[name='contextPath']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(event, xhr, options) {
		xhr.setRequestHeader(csrfHeaderName, csrfToken);
	});

	function countItem(id, act, cntLbl) {
		$.ajax(contextPath + "/nogiitem/" + act, {
			type : "POST",
			data : {
				"id" : id
			},
			dataType : "json"
		}).done(function(data) {
			$("#" + cntLbl).text(data);
		}).fail(function(xhr) {
			var messages = "";
			if (400 <= xhr.status && xhr.status <= 499) {
				var contentType = xhr.getResponseHeader('Content-Type');
				if (contentType != null && contentType.indexOf("json") != -1) {
					json = $.parseJSON(xhr.responseText);
					$(json.errorResults).each(function(i, errorResult) {
						messages += ("<div>" + errorResult.message + "</div>");
					});
				} else {
					messages = ("<div>" + xhr.statusText + "</div>");
				}
			} else {
				messages = ("<div>" + "System error occurred." + "</div>");
			}
			$("#" + cntLbl).html(messages);
		});

		return false;
	}

    function deleteItem(id, rowId) {
        $.ajax(contextPath + "/nogiitem/delete", {
            type : "POST",
            data : {
                "id" : id
            },
            dataType : "json"
        }).done(function(data) {
            $("#" + rowId).css("display", "none");
        }).fail(function(xhr) {
            var messages = "";
            if (400 <= xhr.status && xhr.status <= 499) {
                var contentType = xhr.getResponseHeader('Content-Type');
                if (contentType != null && contentType.indexOf("json") != -1) {
                    json = $.parseJSON(xhr.responseText);
                    $(json.errorResults).each(function(i, errorResult) {
                        messages += ("<div>" + errorResult.message + "</div>");
                    });
                } else {
                    messages = ("<div>" + xhr.statusText + "</div>");
                }
            } else {
                messages = ("<div>" + "System error occurred." + "</div>");
            }
            messages = ("<tr><td>" + messages + "</td></tr>");
            $("#" + rowId).html(messages);
        });

        return false;
    }
</script>
