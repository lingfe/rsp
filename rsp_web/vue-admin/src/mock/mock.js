import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import { LoginUsers, Users ,Bm} from './data/user';
import {Area} from './data/area';
let _Users = Users,_bms=Bm,_Area=Area;

export default {
  /**
   * mock bootstrap
   */
  bootstrap() {
  	return '';
    let mock = new MockAdapter(axios);

    // mock success request
    mock.onGet('/success').reply(200, {
      msg: 'success'
    });

    // mock error request
    mock.onGet('/error').reply(500, {
      msg: 'failure'
    });

    //登录
    mock.onPost('/login').reply(config => {
      let {username, password} = JSON.parse(config.data);
      return new Promise((resolve, reject) => {
        let user = null;
        setTimeout(() => {
          let hasUser = LoginUsers.some(u => {
            if (u.username === username && u.password === password) {
              user = JSON.parse(JSON.stringify(u));
              user.password = undefined;
              return true;
            }
          });

          if (hasUser) {
            resolve([200, { code: 200, msg: '请求成功', user }]);
          } else {
            resolve([200, { code: 500, msg: '账号或密码错误' }]);
          }
        }, 1000);
      });
    });
    //修改密码
    mock.onPost('/changePwd').reply(config => {
    	let {origin,newpwd,newpwd2}=JSON.parse(config.data);
    	return new Promise((resolve,reject) => {
    		setTimeout(() => {
    		if(origin!=123456)resolve([201,{code:201,msg:'当前密码错误'}]);
    		else resolve([200,{code:200,msg:'修改成功'}]);
    		},1000);
    	});
    });
    
    //获取区域信息接口
    mock.onPost('/bm/findArea').reply(config => {
    	let {type,pid,cid,searchkey}=JSON.parse(config.data).params;
			let res=[];
			var back=function(res){
				return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
          	success:true,
          	msg:'请求成功',
            data:res 
          }]);
        }, 1000);
      });
			}
			if(type==0){
				res=_Area.filter(item => item.name.indexOf(searchkey)>-1);
				return back(res);
			}
			let chooseP=_Area.filter(item => item.code==pid);
			if(type==1){
				res=chooseP[0].cityList.filter(item => item.name.indexOf(searchkey)>-1);
				return back(res);
			}
			let chooseC=chooseP[0].cityList.filter(item => item.code==cid);
			if(type==2){
				res=chooseC[0].areaList.filter(item => item.name.indexOf(searchkey)>-1);
				return back(res);
			}
    });
     //新增区域
    mock.onPost('/bm/createArea').reply(config => {
    	let {name,pid,cid,type}=JSON.parse(config.data).params;
    	return new Promise((resolve,reject) => {
    		setTimeout(() => {
    		resolve([200,{code:200,msg:'创建成功',success:true}]);
    		},1000);
    	});
    });
    //获取编码信息接口
    mock.onPost('/bm/getbm').reply(config => {
    	let {type}=JSON.parse(config.data).params;
      let mockbms =_bms.filter((item,i) => type*10<i&&i<(type-0+1)*10 ); 
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
          	success:true,
            bms: mockbms
          }]);
        }, 1000);
      });
    });
    
    //新增编码
    mock.onPost('/bm/createBm').reply(config => {
    	let {name,bz}=JSON.parse(config.data).params;
    	let hasBm=_bms.filter(item => item.name==name);
    	return new Promise((resolve,reject) => {
    		setTimeout(() => {
    		if(hasBm.length)return resolve([200,{code:201,msg:'编码已经存在',success:false}]);
    		if(bz.length>20)return resolve([200,{code:202,msg:'编码备注不能超过20个字符',success:false}]);
    		resolve([200,{code:200,msg:'创建成功',success:true}]);
    		},1000);
    	});
    });
    //修改编码
    mock.onPost('/bm/editBm').reply(config => {
    	let {tysm,bz,upflag}=JSON.parse(config.data).params;
    	return new Promise((resolve,reject) => {
    		setTimeout(() => {
    		if(upflag||upflag==0)return resolve([200,{code:200,msg:'操作成功',success:true}]); 
    		if(bz.length>20)return resolve([200,{code:202,msg:'编码备注不能超过20个字符',success:false}]);
    		if(tysm.length>20)return resolve([200,{code:202,msg:'停用说明不能超过20个字符',success:false}]);
    		resolve([200,{code:200,msg:'修改成功',success:true}]);
    		},1000);
    	});
    });
    //修改编码
    mock.onPost('/bm/deleteBm').reply(config => {
    	return new Promise((resolve,reject) => {
    		setTimeout(() => {
    		resolve([200,{code:200,msg:'删除成功',success:true}]);
    		},1000);
    	});
    });

    //获取用户列表
    mock.onGet('/user/list').reply(config => {
      let {name} = config.params;
      let mockUsers = _Users.filter(user => {
        if (name && user.name.indexOf(name) == -1) return false;
        return true;
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            users: mockUsers
          }]);
        }, 1000);
      });
    });

    //获取用户列表（分页）
    mock.onGet('/user/listpage').reply(config => {
      let {page, name} = config.params;
      let mockUsers = _Users.filter(user => {
        if (name && user.name.indexOf(name) == -1) return false;
        return true;
      });
      let total = mockUsers.length;
      mockUsers = mockUsers.filter((u, index) => index < 20 * page && index >= 20 * (page - 1));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            total: total,
            users: mockUsers
          }]);
        }, 1000);
      });
    });

    //删除用户
    mock.onGet('/user/remove').reply(config => {
      let { id } = config.params;
      _Users = _Users.filter(u => u.id !== id);
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '删除成功'
          }]);
        }, 500);
      });
    });

    //批量删除用户
    mock.onGet('/user/batchremove').reply(config => {
      let { ids } = config.params;
      ids = ids.split(',');
      _Users = _Users.filter(u => !ids.includes(u.id));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '删除成功'
          }]);
        }, 500);
      });
    });

    //编辑用户
    mock.onGet('/user/edit').reply(config => {
      let { id, name, addr, age, birth, sex } = config.params;
      _Users.some(u => {
        if (u.id === id) {
          u.name = name;
          u.addr = addr;
          u.age = age;
          u.birth = birth;
          u.sex = sex;
          return true;
        }
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '编辑成功'
          }]);
        }, 500);
      });
    });

    //新增用户
    mock.onGet('/user/add').reply(config => {
      let { name, addr, age, birth, sex } = config.params;
      _Users.push({
        name: name,
        addr: addr,
        age: age,
        birth: birth,
        sex: sex
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '新增成功'
          }]);
        }, 500);
      });
    });

  }
};