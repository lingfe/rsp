import axios from 'axios';
import Config from '../config.js';
import util from '../common/js/util.js'
import {Vm} from '../main.js';
//axios.defaults.baseURL = 'http://rsp.xinxikeji.net:8880/rsp_sys/';
//axios.defaults.baseURL = 'http://127.0.0.1:8082/rsp_sys/';
axios.defaults.baseURL = 'http://192.168.31.156:8081/rsp_sys/';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });
axios.interceptors.response.use(function (response) {
//	if(response.status==200&&response.data.state==0){
//		Vm.$message({
//			message:response.data.msg,
//			type:'error'
//		});
//		Vm.$router.push('/login');
//  	return Promise.reject('');
//	}
    return response;
  }, function (error) {
    // 对响应错误做点什么
    util.errorShow(error);
    return Promise.reject(error);
  });
let base ='';//Config.base;

export const requestLogin = params => { return util.myPost(`${base}/userinfo/getLogin`,params).then(res => res.data); };
export const requestLoginOut = params => { return util.myPost(`${base}/userinfo/quitSystem`,params).then(res => res.data); };

export const changePwd = params => {return util.myPost(`${base}/userinfo/updatePwd`,params).then(res => res.data);}

export const getUserList = params => { return axios.get(`${base}/user/list`, { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/user/listpage`, { params: params }); };

export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }); };

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };

export const editUser = params => { return axios.get(`${base}/user/edit`, { params: params }); };

export const addUser = params => { return axios.get(`${base}/user/add`, { params: params }); };

export const getBmPerson = params => {return util.myPost(`${base}/coding/getPage`,params).then(res => res.data)};
export const getBmPersonYp = params => {return util.myPost(`${base}/coding/getWhere`,params).then(res => res.data)};
export const createNewBm = params => {return util.myPost(`${base}/coding/save`,params).then(res => res.data)};
export const editBm = params => {return util.myPost(`${base}/coding/update`,params).then(res => res.data)};
export const editBmUp = params => {return util.myPost(`${base}/coding/asc`,params).then(res => res.data)};
export const editBmDown = params => {return util.myPost(`${base}/coding/desc`,params).then(res => res.data)};
export const deleteBm = params => {return util.myPost(`${base}/coding/deleteWhereId`,params).then(res => res.data)};

export const getBmPersonDe = params => {return util.myPost(`${base}/sys_department_coding/getPage`,params).then(res => res.data)};
export const createNewBmDe = params => {return util.myPost(`${base}/sys_department_coding/save`,params).then(res => res.data)};
export const editBmDe = params => {return util.myPost(`${base}/sys_department_coding/update`,params).then(res => res.data)};
export const editBmUpDe = params => {return util.myPost(`${base}/sys_department_coding/asc`,params).then(res => res.data)};
export const editBmDownDe = params => {return util.myPost(`${base}/sys_department_coding/desc`,params).then(res => res.data)};
export const deleteBmDe = params => {return util.myPost(`${base}/sys_department_coding/deleteWhereId`,params).then(res => res.data)};

export const getBmPersonDeG = params => {return util.myPost(`${base}/t_department_management/getPage`,params).then(res => res.data)};
export const createNewBmDeG = params => {return util.myPost(`${base}/t_department_management/save`,params).then(res => res.data)};
export const editBmDeG = params => {return util.myPost(`${base}/t_department_management/update`,params).then(res => res.data)};
export const editBmUpDeG = params => {return util.myPost(`${base}/t_department_management/asc`,params).then(res => res.data)};
export const editBmDownDeG = params => {return util.myPost(`${base}/t_department_management/desc`,params).then(res => res.data)};
export const deleteBmDeG = params => {return util.myPost(`${base}/t_department_management/deleteWhereId`,params).then(res => res.data)};
export const detailBmDeG = params => {return util.myPost(`${base}/t_department_management/getWhereId`,params).then(res => res.data)};

export const getBmPersonPr = params => {return util.myPost(`${base}/manufacturer/pageSelect`,params).then(res => res.data)};
export const createNewBmPr = params => {return util.myPost(`${base}/manufacturer/save`,params).then(res => res.data)};
export const editBmPr = params => {return util.myPost(`${base}/manufacturer/update`,params).then(res => res.data)};
export const editBmUpPr = params => {return util.myPost(`${base}/sys_department_coding/asc`,params).then(res => res.data)};
export const editBmDownPr = params => {return util.myPost(`${base}/sys_department_coding/desc`,params).then(res => res.data)};
export const deleteBmPr = params => {return util.myPost(`${base}/manufacturer/deleteWhereId`,params).then(res => res.data)};

export const getEquipList = params => {return util.myPost(`${base}/t_equipment_archives/pageSelect`,params).then(res => res.data)};
export const createNewEquip = params => {return util.myPost(`${base}/t_equipment_archives/save`,params).then(res => res.data)};
export const editEquip = params => {return util.myPost(`${base}/t_equipment_archives/update`,params).then(res => res.data)};


export const findArea = params => {return axios.post(`${base}/bm/findArea`,{params:params}).then(res => res.data)};
export const createArea = params => {return axios.post(`${base}/bm/createArea`,{params:params}).then(res => res.data)};
