$(function() {
	$('#sginInForm').formValidation({
		framework : 'bootstrap',
		icon : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		// This option will not ignore invisible fields which
		// belong to inactive panels
		excluded : ':disabled',
		fields : {
			userName : {
				validators : {
					notEmpty : {
						message : '请输入名字'
					}
				}
			},
			password : {
				validators : {
					notEmpty : {
						message : '请输入5位数字口令.'
					},
					digits : {
						message : '请输入5位数字口令.'
					},
					stringLength : {
						min : 5,
						max : 5,
						message : ' '
					}
				}
			}
		}
	});
});