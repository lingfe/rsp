import Login from './views/Login.vue'
import HosLogin from './views/HosLogin.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Dia from './views/comp/dialog.vue'
import Table from './views/nav1/Table.vue'
import Form from './views/nav1/Form.vue'
import user from './views/nav1/user.vue'
import Page4 from './views/nav2/Page4.vue'
import Page5 from './views/nav2/Page5.vue'
import PersonCode from './views/bmgl/bm_person.vue'
import EquipCode from './views/bmgl/bm_equip.vue'
import LicenseCode from './views/bmgl/bm_license.vue'
import ProgressCode from './views/bmgl/bm_progress.vue'
import AreaCode from './views/bmgl/bm_area.vue'
import ProviderCode from './views/bmgl/bm_provider.vue'
import DepartCode from './views/bmgl/bm_depart.vue'
import DepartManage from './views/departgl/departManage.vue'
import equipManage from './views/equipgl/equipManage.vue'
import Page7 from './views/nav2/myELeTableTry.vue'
import Page6 from './views/nav3/Page6.vue'
import Page8 from './views/nav3/Page8.vue'
import echarts from './views/charts/echarts.vue'

let flag=1;
export const routes = [
	{path: '/login',component: Login,name:'登录'},
	{path: '/login_hospital',component: HosLogin,name:'登录-医院'},
	{path: '/404',component: NotFound,name:'404'},
	{path: '*',redirect: { path: '/404' }},
	{
		path: '/',
        component: Home,
        iconCls: 'el-icon-message',//图标样式class
        name:'主页',
        children: [
            {path: '/main',component: Main,name:'主页'},
			{path: '/table',component: Table,name:'表格'},
			{path: '/form',component: Form,name:'表单'},
			{path: '/user',component: user,name:'用户？'},
			{path: '/dialog',component: Dia,name:'弹窗'},
			{path: '/page4',component: Page4,name:"页面4"},
			{path: '/page5',component: Page5,name:"页面5"},
			{path: '/page6',component: Page6,name:"页面6"},
			{path: '/page7',component: Page7,name:"table组件"},
			{path: '/page8',component: Page8,name:"编辑"},
		    { path: '/bm_person', component: PersonCode,name: '人员编码'},
		    { path: '/bm_equip', component: EquipCode, name: '设备编码'},
		    { path: '/bm_license', component: LicenseCode, name: '证照编码' },
		    { path: '/bm_progress', component: ProgressCode, name: '流程编码'},
		    { path: '/bm_depart', component: DepartCode, name: '科室编码'},
		    { path: '/bm_area', component: AreaCode, name: '区域信息'},
		    { path: '/bm_provider', component: ProviderCode, name: '生产厂家'},
		    { path: '/depart_manage', component: DepartManage, name: '科室管理'},
		    { path: '/equip_manage', component: equipManage, name: '设备档案'}
        ]
	},
];
export const routes1 = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/login_hospital',
        component: HosLogin,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
     {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'fa fa-home',
        leaf: true,//只有一个节点
        childString:'/main',
        children: [
            { path: '/main', component: Main, name: '主页' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '导航一',
        iconCls: 'el-icon-message',//图标样式class
        childString:'/main/table/form/user/dialog/',
        hidden: true ,
        children: [
            { path: '/main', component: Main, name: '主页', hidden: true },
            { path: '/table', component: Table, name: 'Table', hidden: true  },
            { path: '/form', component: Form, name: 'Form' },
            { path: '/user', component: user, name: '列表' , hidden: true },
            { path: '/dialog', component: Dia, name: '弹窗' },
        ]
    },
    {
        path: '/',
        component: Home,
        hidden: true ,
        name: '导航二',
        iconCls: 'fa fa-id-card-o',
        childString:'/page4/page5/page7',
        children: [
            { path: '/page4', component: Page4, name: '页面4' },
            { path: '/page5', component: Page5, name: '页面5' },
            { path: '/page7', component: Page7, name: 'table组件' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '页面编辑',
        iconCls: 'fa fa-address-card',
        hidden: true ,
//      leaf: true,//只有一个节点
        childString:'/page6/dialog/page8',
        children: [
            { path: '/page6', component: Page6, name: '导航三' },
            { path: '/page8', component: Page8, name: '编辑' },
            { path: '/dialog', component: Dia, name: '弹窗' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '编码管理',
        childString:'/bm_person/bm_equip/bm_license/bm_progress/bm_depart/bm_area/bm_provider',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/bm_person', component: PersonCode,name: '人员编码'},
            { path: '/bm_equip', component: EquipCode, name: '设备编码'},
            { path: '/bm_license', component: LicenseCode, name: '证照编码' },
            { path: '/bm_progress', component: ProgressCode, name: '流程编码'},
            { path: '/bm_depart', component: DepartCode, name: '科室编码', hidden: true},
            { path: '/bm_area', component: AreaCode, name: '区域信息' , hidden: true},
            { path: '/bm_provider', component: ProviderCode, name: '生产厂家', hidden: true}
        ]
    },
//  {
//      path: '/',
//      component: Home,
//      name: 'Charts',
//      iconCls: 'fa fa-bar-chart',
//      children: [
//          { path: '/echarts', component: echarts, name: 'echarts' }
//      ]
//  },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export const routes2=[
	{
        path: '/',
        component: Home,
        name: '',
        iconCls: 'fa fa-home',
        leaf: true,//只有一个节点
        childString:'/main',
        children: [
            { path: '/main', component: Main, name: '主页' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '人员管理',
        iconCls: 'fa fa-id-card-o',
        childString:'/bm_person',
        children: [
            { path: '/bm_person', component: PersonCode,name: '人员编码'}
        ]
    },
    {
        path: '/',
        component: Home,
        name: '设备管理',
        iconCls: 'fa fa-id-card-o',
        childString:'/bm_equip/equip_manage',
        children: [
		    { path: '/equip_manage', component: equipManage, name: '设备档案'},
            { path: '/bm_equip', component: EquipCode, name: '设备编码'}
        ]
    },
    {
        path: '/',
        component: Home,
        name: '证照管理',
        iconCls: 'fa fa-id-card-o',
        childString:'/bm_license',
        children: [
            { path: '/bm_license', component: LicenseCode, name: '证照编码' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '科室管理',
//      hidden: true,
        iconCls: 'fa fa-id-card-o',
        childString:'/bm_depart/depart_manage',
        children: [
		    { path: '/depart_manage', component: DepartManage, name: '科室管理'},
            { path: '/bm_depart', component: DepartCode, name: '科室编码' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '其他信息维护',
        iconCls: 'fa fa-id-card-o',
        childString:'/bm_provider',
//       hidden: true,
        children: [
            { path: '/bm_provider', component: ProviderCode, name: '生产厂家'}
        ]
    }
];

//export default routes;