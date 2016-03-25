<form class="form-horizontal">
<#list players as p>
  <div class="form-group">
    <label for="input${p_index}" " class="col-sm-2 control-label">${p.userName}</label>
     <div class="col-sm-2">
      <input type="email" class="form-control" id="input${p_index}" placeholder="积分" value="${taskRecive.pointAve[p_index]}"}>
    </div>
  </div>
</#list>
  <button id="pointGiveOut" type="submit" class="btn btn-default">分配</button>
</form>