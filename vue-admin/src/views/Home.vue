<template>
	<el-row class="container">
		<el-col :span="24" class="header">
			<el-col :span="10" class="logo" :class="collapsed?'logo-collapse-width':'logo-width'">
				{{collapsed?'':sysName}}
			</el-col>
			<el-col :span="10">
				<div class="tools" @click.prevent="collapse">
					<i class="fa fa-align-justify"></i>
				</div>
			</el-col>
			<el-col :span="4" class="userinfo">
				<el-dropdown trigger="hover">
					<span class="el-dropdown-link userinfo-inner"><img :src="this.sysUserAvatar" /> {{sysUserName}}</span>
					<el-dropdown-menu slot="dropdown">
						<!--<el-dropdown-item>我的消息</el-dropdown-item>-->
						<el-dropdown-item @click.native='changecode'>修改密码</el-dropdown-item>
						<el-dropdown-item @click.native='changeHos'>切换医院</el-dropdown-item>
						<el-dropdown-item @click.native='changeRui'>切换瑞达</el-dropdown-item>
						<el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-col>
		</el-col>
		<el-col :span="24" class="main">
			<aside :class="collapsed?'menu-collapsed':'menu-expanded'">
				<!--导航菜单-->
				<el-menu :default-active="$route.path" class="el-menu-vertical-demo" @open="handleopen" @close="handleclose" @select="handleselect"  ref='menu'
					 unique-opened router v-show="!collapsed">
					<template v-for="(item,index) in routerArray" v-if="!item.hidden">
						<el-submenu :index="index+''" v-if="!item.leaf">
							<template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>
							<el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden">{{child.name}}</el-menu-item>
						</el-submenu>
						<el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path"><i :class="item.iconCls"></i>{{item.children[0].name}}</el-menu-item>
					</template>
				</el-menu>
				<!--导航菜单-折叠后-->
				<ul class="el-menu el-menu-vertical-demo collapsed" v-show="collapsed" ref="menuCollapsed">
					<li v-for="(item,index) in routerArray" v-if="!item.hidden" class="el-submenu item">
						<template v-if="!item.leaf">
							<div class="el-submenu__title" style="padding-left: 20px;" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"><i :class="item.iconCls"></i></div>
							<ul class="el-menu submenu" :class="'submenu-hook-'+index" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"> 
								<li v-for="child in item.children" v-if="!child.hidden" :key="child.path" class="el-menu-item" style="padding-left: 40px;" :class="$route.path==child.path?'is-active':''" @click="$router.push(child.path)">{{child.name}}</li>
							</ul>
						</template>
						<template v-else>
							<li class="el-submenu">
								<div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;" :class="$route.path==item.children[0].path?'is-active':''" @click="$router.push(item.children[0].path)"><i :class="item.iconCls"></i></div>
							</li>
						</template>
					</li>
				</ul>
			</aside>
			<section class="content-container" >
				<div class="grid-content bg-purple-light">
					<el-col :span="24" class="breadcrumb-container">
						<strong class="title">{{$route.name}}</strong>
						<el-breadcrumb separator="/" class="breadcrumb-inner">
							<el-breadcrumb-item v-for="item in calcName()" :key="item.path">
								{{ item.name }}
							</el-breadcrumb-item>
						</el-breadcrumb>
					</el-col>
					<el-col :span="24" class="content-wrapper">
						<transition name="fade" mode="out-in">
							<router-view></router-view>
						</transition>
					</el-col>
					 <el-dialog title="修改密码" :visible.sync="dialogVisible" width="30%">
				      <el-form ref="pwdform" :model="pwdform" :rules='changeRule' label-width="120px">
				        <el-form-item label="当前密码" prop='origin'>
				          <el-input v-model="pwdform.origin" type='password'></el-input>
				        </el-form-item>
				        <el-form-item label="输入新密码" prop='newpwd'>
				          <el-input v-model="pwdform.newpwd" type='password'></el-input>
				        </el-form-item>
				        <el-form-item label="再次输入新密码" prop='newpwd2'>
				          <el-input v-model="pwdform.newpwd2" type='password'></el-input>
				        </el-form-item>
				      </el-form>
				      <span slot="footer" class="dialog-footer">
				        <el-button @click="dialogVisible = false">取 消</el-button>
				        <el-button type="primary" @click="savepwd">确 定</el-button>
				      </span>
				    </el-dialog>
				</div>
			</section>
		</el-col>
	</el-row>
</template>

<script>
	import {changePwd,requestLoginOut} from '../api/api.js'
	import {routes1,routes2} from '../routes.js'
	import { Loading } from 'element-ui';
	
	export default {
		data() {
			var validatorNewpwd = (rule,value,callback) => {
				if(value.length>10||value.length<4)return callback(new Error('密码应在4-10个字符间'));
				callback();
			}
			var validatorNewpwd2 = (rule,value,callback) => {
				if(value.length>10||value.length<4)return callback(new Error('密码应在4-10个字符间'));
				if(value!=this.pwdform.newpwd)return callback(new Error('两次密码不一致，请重新输入'));
				callback();
			}
			return {
				routerArray:[],
				sysName:'瑞达检测',
				collapsed:false,
				sysUserName: '',
				sysUserAvatar: '',
				dialogVisible:false,
				pwdform:{
					origin:'',
					newpwd:'',
					newpwd2:''
				},
				hosFlag:0,
				form: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				},
				changeRule:{
					origin:[{ required: true, message: '当前密码不能为空',trigger:'blur'}],
					newpwd:[
					{ required: true, message: '新密码不能为空',trigger:'blur'},
					{ validator:validatorNewpwd,trigger:'blur'}
					],
					newpwd2:[
					{ required: true, message: '请再次输入密码验证',trigger:'blur'},
					{ validator:validatorNewpwd2,trigger:'blur'}
					]
				}
			}
		},
		methods: {
			savepwd(){
				var $this=this;
				this.$refs.pwdform.validate(valid => {
					if(valid){
						var para={
							username:JSON.parse(sessionStorage.getItem('user')).username,
							pwd:this.pwdform.origin,
							newPwd:this.pwdform.newpwd
						}
					changePwd(para).then(res => {
						let {msg,state} = res;
						if(state != 200){
							 this.$message({
			                  message: msg,
			                  type: 'error'
			                });
						}else{
							this.$message({
			                  message: '密码修改成功',
			                  type: 'success'
			                });
			                this.dialogVisible=false;
						}
					});
					}
				});
			},
			onSubmit() {
				console.log('submit!');
			},
			handleopen() {
				//console.log('handleopen');
			},
			handleclose() {
				
			},	
			calcName(){
				let $this=this;
				let parentR=this.routerArray.filter(item => item.childString&&item.childString.indexOf($this.$route.path)>-1)[0];
				if(!parentR)return [{name:''}];
				let childrenR=parentR.children.filter(item => item.path==$this.$route.path)[0];
				return [parentR,childrenR];
			},
			handleselect: function (a, b) {
			},
			//退出登录
			logout: function () {
				var _this = this;
				let hosFlag=sessionStorage.getItem('hosFlag')||0;
				this.$confirm('确认退出吗?', '提示', {
					//type: 'warning'
				}).then(() => {
					requestLoginOut({}).then(res => {
						let {msg,state}=res;
						if(state!=200){
							_this.$message({
								message:msg,
								type:'error'
							});
							return false;
						}
						sessionStorage.removeItem('user');
						if(hosFlag!=1)_this.$router.push('/login');
						else _this.$router.push('/login_hospital');
					});
				}).catch(() => {
					sessionStorage.removeItem('user');
					if(hosFlag!=1)_this.$router.push('/login');
					else _this.$router.push('/login_hospital');
				});
			},
			changecode(){
				this.dialogVisible=true;
				this.pwdform={
					origin:'',
					newpwd:'',
					newpwd2:''
				};
			},
			changeHos(){
				let loading=Loading.service({fullscreen:true});
				this.routerArray=routes2;
				sessionStorage.hosFlag=1;
				this.collapsed=false;
				this.sysName='瑞达检测-医院端';
				this.closeMenu();
				this.$router.push('/main');
				setTimeout(() => {loading.close()},1500);
			},
			changeRui(){
				let loading=Loading.service({fullscreen:true});
				this.routerArray=routes1;
				sessionStorage.hosFlag=0;
				this.collapsed=false;
				this.sysName='瑞达检测';
				this.closeMenu();
				this.$router.push('/main');
				setTimeout(() => {loading.close()},1500);
			},
			closeMenu(){
				let $this=this;
				$this.$refs.menu.openedMenus.forEach((v) => {
					$this.$refs.menu.closeMenu(v);
				});
			},
			//折叠导航栏
			collapse:function(){
				this.collapsed=!this.collapsed;
			},
			showMenu(i,status){
				this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-'+i)[0].style.display=status?'block':'none';
			}
		},
		mounted() {
			var user = sessionStorage.getItem('user');
			if (user) {
				user = JSON.parse(user);
				this.sysUserName = user.name || '';
				this.sysUserAvatar = user.avatar || '';
			}
			if(this.hosFlag==0)this.routerArray=routes1;
			else this.routerArray=routes2;
		},
		beforeMount(){
			if(!sessionStorage.getItem('hosFlag')){
				sessionStorage.hosFlag=0;
			}
			this.hosFlag=sessionStorage.getItem('hosFlag')==1?1:0;
			this.sysName=this.hosFlag==1?'瑞达检测-医院端':'瑞达检测';
		}
	}

</script>
<style>
	.collapsed .el-submenu .el-menu-item{
		min-width:auto;
	}
	.menu-expanded .el-menu.el-menu-vertical-demo{
		width:auto!important;
	}
	.el-menu-item.is-active{
		background-color: #d1dbe5;
	}
	.el-dialog--small{
		width:400px;
	}
</style>

<style scoped lang="scss">
	@import '~scss_vars';
	
	.container {
		position: absolute;
		top: 0px;
		bottom: 0px;
		width: 100%;
		.header {
			height: 60px;
			line-height: 60px;
			background: $color-primary;
			color:#fff;
			.userinfo {
				text-align: right;
				padding-right: 35px;
				float: right;
				.userinfo-inner {
					cursor: pointer;
					color:#fff;
					img {
						width: 40px;
						height: 40px;
						border-radius: 20px;
						margin: 10px 0px 10px 10px;
						float: right;
					}
				}
			}
			.logo {
				//width:230px;
				height:60px;
				font-size: 22px;
				padding-left:20px;
				padding-right:20px;
				border-color: rgba(238,241,146,0.3);
				border-right-width: 1px;
				border-right-style: solid;
				img {
					width: 40px;
					float: left;
					margin: 10px 10px 10px 18px;
				}
				.txt {
					color:#fff;
				}
			}
			.logo-width{
				width:230px;
			}
			.logo-collapse-width{
				width:60px
			}
			.tools{
				padding: 0px 23px;
				width:14px;
				height: 60px;
				line-height: 60px;
				cursor: pointer;
			}
		}
		.main {
			display: flex;
			// background: #324057;
			position: absolute;
			top: 60px;
			bottom: 0px;
			overflow: hidden;
			aside {
				flex:0 0 230px;
				width: 230px;
				// position: absolute;
				// top: 0px;
				// bottom: 0px;
				.el-menu{
					height: 100%;
				}
				.collapsed{
					width:60px;
					.item{
						position: relative;
					}
					.submenu{
						position:absolute;
						top:0px;
						left:60px;
						z-index:99999;
						height:auto;
						display:none;
					}

				}
			}
			.menu-collapsed{
				flex:0 0 60px;
				width: 60px;
			}
			.menu-expanded{
				flex:0 0 230px;
				width: 230px;
			}
			.content-container {
				// background: #f1f2f7;
				flex:1;
				// position: absolute;
				// right: 0px;
				// top: 0px;
				// bottom: 0px;
				// left: 230px;
				overflow-y: auto;
				padding: 20px;
				.breadcrumb-container {
					//margin-bottom: 15px;
					.title {
						width: 200px;
						float: left;
						color: #475669;
					}
					.breadcrumb-inner {
						float: right;
					}
				}
				.content-wrapper {
					background-color: #fff;
					box-sizing: border-box;
				}
			}
		}
	}
</style>