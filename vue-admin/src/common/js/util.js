var SIGN_REGEXP = /([yMdhsm])(\1*)/g;
var DEFAULT_PATTERN = 'yyyy-MM-dd';
import axios from 'axios';
import {Vm} from '../../main.js';
import qs from 'qs';
function padding(s, len) {
    var len = len - (s + '').length;
    for (var i = 0; i < len; i++) { s = '0' + s; }
    return s;
};

export default {
	errorShow(str){
		Vm.$message({
			message:str,
			type:'error'
		});
	},
	translateDataToTree(data,parentKey,keyword) {
		let parents = data.filter(d => {
			let hasParent = false;
			data.forEach((v,k) => {
                if (v[keyword] == d[parentKey]) hasParent = true;
			});
            return !hasParent;
		});
        let children = data;
        let translator = function(parents, children){
            parents.forEach(function(parent){
                children.forEach(function(current, index){
                    if (current[parentKey] === parent[keyword]) {
                        var temp = JSON.parse(JSON.stringify(children))
                        temp.splice(index, 1)
                        translator([current], temp)
                        typeof parent.children !== 'undefined' ? parent.children.push(current) : parent.children = [current]
                    }
                }
                )
            }
            )
        }
        translator(parents, children)
        return parents
        },
        
	myPost:function(url,params){
		return axios({method:'post',url:url,data:qs.stringify(params)});
	},
    getQueryStringByName: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        var context = "";
        if (r != null)
            context = r[2];
        reg = null;
        r = null;
        return context == null || context == "" || context == "undefined" ? "" : context;
    },
    heightCalc:function(elename1,two){
    	if(typeof two!='object')return '';
    	var res=document.querySelectorAll(elename1)[0].offsetHeight;
    	two.forEach(function(v,k){
    		if(typeof v=='string')res-=document.querySelectorAll(v)[0].offsetHeight;
    		else res-=v;
    	});
    	return res;
    },
    formatDate: {
        format: function (date, pattern) {
            pattern = pattern || DEFAULT_PATTERN;
            return pattern.replace(SIGN_REGEXP, function ($0) {
                switch ($0.charAt(0)) {
                    case 'y': return padding(date.getFullYear(), $0.length);
                    case 'M': return padding(date.getMonth() + 1, $0.length);
                    case 'd': return padding(date.getDate(), $0.length);
                    case 'w': return date.getDay() + 1;
                    case 'h': return padding(date.getHours(), $0.length);
                    case 'm': return padding(date.getMinutes(), $0.length);
                    case 's': return padding(date.getSeconds(), $0.length);
                }
            });
        },
        parse: function (dateString, pattern) {
            var matchs1 = pattern.match(SIGN_REGEXP);
            var matchs2 = dateString.match(/(\d)+/g);
            if (matchs1.length == matchs2.length) {
                var _date = new Date(1970, 0, 1);
                for (var i = 0; i < matchs1.length; i++) {
                    var _int = parseInt(matchs2[i]);
                    var sign = matchs1[i];
                    switch (sign.charAt(0)) {
                        case 'y': _date.setFullYear(_int); break;
                        case 'M': _date.setMonth(_int - 1); break;
                        case 'd': _date.setDate(_int); break;
                        case 'h': _date.setHours(_int); break;
                        case 'm': _date.setMinutes(_int); break;
                        case 's': _date.setSeconds(_int); break;
                    }
                }
                return _date;
            }
            return null;
        }

    }

};
