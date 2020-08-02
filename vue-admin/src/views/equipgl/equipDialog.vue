<template>
		<el-dialog :data='showData'  :title="calcTitle()" :visible.sync="showData.dialogVisible" :close-on-click-modal='false' class='equip_dia'>
			<el-tabs v-model="showData.tab" @tab-click="handleClick" >
			    <el-tab-pane label="设备信息" name="first">
			    	<el-form ref="form" :model="form" :inline='true' label-width='100px' :rules='rules'>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="设备名称" prop='sb_name'>
										<el-input v-model='form.sb_name'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="规格型号" prop='sb_ggxh'>
										<el-input v-model='form.sb_ggxh'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="设备编号" prop='sb_code'>
										<el-input v-model='form.sb_code'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="设备类别" prop='sb_zzlb_id'>
										<el-select v-model="form.sb_zzlb_id">
									      <el-option v-for='(item,i) in lbArr' :label="item.coding_name" :key='item.id' :value="item.id"></el-option>
									    </el-select>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='8'>
									<el-form-item label="额定电压（KV）" prop='sb_eddy'>
										<el-input v-model='form.sb_eddy' type='number'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='8'>
									<el-form-item label="生产厂家" prop='sb_manufacturer_id'>
										<el-select v-model="form.sb_manufacturer_id">
									      <el-option v-for='(item,i) in cjArr' :label="item.manufacturer_name" :key='item.id' :value="item.id"></el-option>
									   </el-select>
									</el-form-item>
								</el-col>
								<el-col :span='8'>
									<el-form-item label="使用科室" prop='sb_syks_id'>
										<el-select v-model="form.sb_syks_id" @change='departChange'>
									      <el-option v-for='(item,i) in ksArr' :label="item.ks_name" :key='item.id' :value="item.id"></el-option>
									  </el-select>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="用途" prop='sb_yt_id'>
										<el-input v-model='form.sb_yt_id' type='textarea' :autosize='{minRows: 1}'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="所在场所" prop='sb_szcs'>
										<el-input v-model='form.sb_szcs'  type='textarea' :autosize='{minRows: 1}'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check' prop='flag_fszlxkz'>
									<el-checkbox v-model='form.flag_fszlxkz'>放射诊疗许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='fszlxkz_date'>
									<el-date-picker
								      v-model="form.fszlxkz_date"
									  :editable='false'
								      type="date"
								      value-format="yyyy-MM-dd"
								      placeholder="">
								    </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check' prop='flag_fsaqxkz'>
									<el-checkbox v-model='form.flag_fsaqxkz'>辐射安全许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='fsaqxkz_date'>
									<el-date-picker
								      v-model="form.fsaqxkz_date"
									  :editable='false'
								      type="date"
								      value-format="yyyy-MM-dd"
								      placeholder="">
								   </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check' prop='flag_dxpzxkz'>
									<el-checkbox v-model='form.flag_dxpzxkz'>大型配置许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='dxpzxkz_date'>
									<el-date-picker
								      v-model="form.dxpzxkz_date"
									  :editable='false'
								      type="date"
								      value-format="yyyy-MM-dd"
								      placeholder="">
								  </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="来源" prop='sb_ly'>
										<el-input v-model='form.sb_ly'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="去向" prop='sb_qx'>
										<el-input v-model='form.sb_qx'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check' prop='is_scrap'>
									<el-checkbox v-model='form.is_scrap'>报废</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="报废时间" prop='scrap_date'>
									<el-date-picker
								      v-model="form.scrap_date"
									  :editable='false'
								      type="date"
								      value-format="yyyy-MM-dd"
								      placeholder="">
								 </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item' v-for='item in ypArr' :key='item.id'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check'>
									<el-checkbox v-model='item.check'  >{{item.yp_name}}</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item :label="item.yp_name+'时间'">
									<el-date-picker
									  value-format="yyyy-MM-dd"
									  :editable='false'
									  disabled
								      type="date"
								      v-model='item.start_date'
								      placeholder="">
								   </el-date-picker>
								</el-form-item>
							</el-col>
							<el-col :span='6'>
								<el-form-item label="周期">
									<el-input :value='item.yp_cycle' type='number'></el-input>
								</el-form-item>
							</el-col>
							<el-col :span='6'>
								<el-form-item label="下次时间">
									<el-date-picker
								      type="date"
									  :editable='false'
									  v-model='item.next_date'
								      value-format="yyyy-MM-dd"
								      placeholder="">
								   </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
      				</el-form>
			    </el-tab-pane>
			    <el-tab-pane label="资料信息" name="second" v-if='showData.type!=0'>
			    	<el-row class='my-form-item'>
								<!--<el-col :span='6'>
									<el-tree :data="tree" :props="defaultProps" @node-click="handleNodeClick" :default-expand-all='true' highlight-current></el-tree>
								</el-col>-->
								<!--<el-col :span='18'>-->
									<my-el-table :table="dataTable"  @onHandleSelectionChange='onHandleSelectionChange' >
										<template slot-scope="props" slot="fj_name_real">
									        <a class="template-username" :rguid='props.obj.row.id' :href='"http://192.168.31.156:8081/rsp_sys/enclosure/fileDownLoad?id="+props.obj.row.id'>{{ props.obj.row.fj_name_real }}</a>
										</template>
									</my-el-table>
								<!--</el-col>-->
			    		</el-row>
			    </el-tab-pane>
			    <el-tab-pane label="痕迹信息" name="third" v-if='showData.type!=0'>
			    	<my-timeline :showData='timeline'></my-timeline>
			    </el-tab-pane>
			</el-tabs>
      
      <span slot="footer" class="dialog-footer" v-show='bottom==1'>
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="sure" :loading='btnLoading'>确 定</el-button>
      </span>
       <span slot="footer" class="dialog-footer" v-show='bottom==2'>
       	<el-upload
       	  class='el-button' style='border:none;padding:0' 
		  action="http://192.168.31.156:8081/rsp_sys/enclosure/fileUpLoadAll"
		  :on-preview="handlePreview"
		  :before-upload="beforeupload"
		  :file-list="fileList">
		  <el-button type="primary">上 传</el-button>
		</el-upload>
        <!--<el-button type="primary" @click="download" :loading='btnLoading'>下 载</el-button>-->
        <el-button type="danger" @click="deletefile" :loading='btnLoading'>删 除</el-button>
        <el-button @click="cancel">关闭</el-button>
      </span>
       <span slot="footer" class="dialog-footer" v-show='bottom==3'>
        <el-button @click="cancel">关闭</el-button>
      </span>
    </el-dialog>
</template>

<script>
	import {getBmPerson,getBmPersonYp,getBmPersonPr,getBmPersonDeG,createNewEquip,editEquip,createNewYp,oneEquip,getHj,editYp,getFj,deleteFj,downFj} from '../../api/api.js'
	import utils from '../../common/js/util.js'
    import axios from 'axios';
	export default{
		name:'departDia',
		props:{
			showData:{
				type:Object,
				default(){
					return{
						type:0,
						dialogVisible:false,
						selectId:'',
						freshFlag:0 //初始化页面的flag，每次打开页面都必须传递。
					}
				}
			}
		},
		data(){
			let $this = this;
			 var check_fszlxkz_date = (rule, value, callback) => {
		        setTimeout(() => {
		        	if($this.form.flag_fszlxkz&&!value){
		        		callback(new Error('请选择取得时间'));
		        	}else callback();
		        }, 1000);
		      };
		       var check_fsaqxkz_date = (rule, value, callback) => {
		        setTimeout(() => {
		        	if($this.form.flag_fsaqxkz&&!value){
		        		callback(new Error('请选择取得时间'));
		        	}else callback();
		        }, 1000);
		      };
		       var check_dxpzxkz_date = (rule, value, callback) => {
		        setTimeout(() => {
		        	if($this.form.flag_dxpzxkz&&!value){
		        		callback(new Error('请选择取得时间'));
		        	}else callback();
		        }, 1000);
		      };
			return {
				fileList:[],
				activeName:'first',
				dialogVisible:true,
				btnLoading:false,
				bottom:1,
				form:{
					sb_name:'',
					sb_ggxh:'',
					sb_code:'',
					sb_zzlb_id:'',
					sb_yt_id:'',
					sb_szcs:'',
					sb_ly:'',
					scrap_date:'',
					sb_qx:'',
					is_scrap:0,
					sb_eddy:'',
					sb_manufacturer_id:'',
					sb_syks_id:'',
					flag_fszlxkz:0,
					fszlxkz_date:'',
					flag_fsaqxkz:0,
					fsaqxkz_date:'',
					flag_dxpzxkz:0,
					dxpzxkz_date:'',
					department_name:''
				},
				rules:{
					sb_name:[{ required: true, message: '请输入设备名称', trigger: 'blur' }],
					sb_code:[{ required: true, message: '请输入设备编号', trigger: 'blur' }],
					fszlxkz_date:[{ validator: check_fszlxkz_date, trigger: 'blur' }],
					fsaqxkz_date:[{ validator: check_fsaqxkz_date, trigger: 'blur' }],
					dxpzxkz_date:[{ validator: check_dxpzxkz_date, trigger: 'blur' }]
				},
				lbArr:[],
				cjArr:[],
				ksArr:[],
				ypArr:[],
				tree:[{label:'资料信息',children:[
					{label:'资料信息',id:1},
					{label:'设备说明书',id:2},
					{label:'医疗器械注册证',id:3},
					{label:'大型配置许可证',id:4},
					{label:'设备铭牌',id:5},
				]}],
				defaultProps:{
					children: 'children',
          			label: 'label'
				},
				dataTable:{
					sels:[],
					loading:false,
					hasSelect:false,
		            tr:[
					{label:'上传时间',prop:'crt_date',width:180},
					{label:'附件类型',prop:'type',width:120},
					{label:'附件名称',prop:'fj_name_real',minWidth:180,show:'template'}
					],
					data:[
						{time:'2018-01-03',type:'年检报告',name:'2018年检报告.pdf'},
						{time:'2018-01-03',type:'设备说明书',name:'设备说明书.pdf'},
						{time:'2018-01-03',type:'设备铭牌',name:'设备铭牌.pdf'}
					]
				},
				timeline:{
					data:[
//						{time:'2018-01-12 12:15:45【张三】',content:'修改设备编号'},
//						{time:'2018-01-12 12:15:45【张三】',content:'修改放射许可证的上证时间'},
//						{time:'2018-01-12 12:15:45【李四】',content:'新增设备信息'}
					]
				}
			}
		},
		methods:{
			cancel(){
				console.log(this.fileList);
			},
			onHandleSelectionChange(val){this.dataTable.sels=val;},
		      handlePreview(file) {
		        console.log(file);
		      },
		      beforeupload(file) {
		      	let $this=this;
                console.log(file);
                let param=new FormData();
                //创建临时的路径来展示图片
                var windowURL = window.URL || window.webkitURL;
                
                this.src=windowURL.createObjectURL(file);
                //重新写一个表单上传的方法
                param.append('file', file, file.name);
                param.append('yw_id',this.form.id);
                 let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                };
                 axios.post("http://192.168.31.156:8081/rsp_sys/enclosure/fileUpLoad", param, config).then(function(result) {
                     if(result.data.state==200){
                     	$this.getfj();
                     }
                })
                return false;
            },
			sure(){
				let $this=this;
				let dateArr=['fszlxkz_date','fsaqxkz_date','dxpzxkz_date','scrap_date'];
				let flagArr=['flag_fszlxkz','flag_fsaqxkz','flag_dxpzxkz','is_scrap'];
				this.$refs.form.validate((valid) => {
				if(!valid)return false;
				$this.ypArr.forEach(d => {
					if($this.form.id)d.equipment_id=$this.form.id;
					else d.equipment_id='';
					d.is_walkthrough=d.check?1:0;
					d.next_time=d.next_time?utils.formatDate.format(new Date(d.next_time),'yy-MM-dd'):'';
				});
				let para=Object.assign({},$this.form,{hospital_id:'1001',hospital_name:'初始'});
				Object.keys(para).forEach((key,i) => {
						if(dateArr.indexOf(key)>-1)para[key]=para[key]?utils.formatDate.format(new Date(para[key]),'yy-MM-dd'):'';
						if(flagArr.indexOf(key)>-1)para[key]=para[key]?1:0;
				});
				if($this.showData.type==0){
					$this.btnLoading=true;
					createNewEquip(para).then(res => {
						$this.btnLoading=false;
						let {state,msg,data}=res;
						if(state!=200){
							$this.$message.error(msg);
							return false;
						}
						$this.saveyp(data.id,0);
					}).catch(e => {
						$this.btnLoading=false;
						$this.$message.error(e);
					});
					return false;
				}
				$this.$confirm('确定这样修改').then(() => {
					$this.btnLoading=true;
					para.id=$this.form.id;
					editEquip(para).then(res => {
						let {state,msg,data}=res;
						$this.btnLoading=false;
						if(state!=200)return $this.$message.error(msg);
						$this.$message({
							message:'修改成功',
							type:'success'
						});
						$this.saveyp($this.form.id,1);
					}).catch(e => {
						$this.btnLoading=false;
						$this.$message.error(e);
					});
					return false;
				});
				});
			},
			handleClick(tab, event) {
				if(tab.name=='first')this.bottom=1;
				if(tab.name=='second'){
					this.bottom=2;
					this.getfj();
				}
				if(tab.name=='third'){
					this.gethj();
					this.bottom=3;
				}
		 },
		 getfj(){
		 	let $this=this;
		 	let id=$this.form.id;
		 	let para={pageIndex:1,pageNum:999,yw_id:id};
		 	$this.dataTable.sels=[];
		 	getFj(para).then(res => {
		 		let {state,msg,data}=res;
				let arr=[];
				if(state!=200){
					$this.timeline.data=arr;
					return $this.$message.error(msg);
				}
		 		$this.dataTable.data=data.list;
		 	}).catch(e => {
		 		$this.$message.error(e);
		 		$this.dataTable.data=[];
		 	});
		 },
		 gethj(){
		 	let id=this.form.id;
		 	let $this=this;
		 	getHj({sb_id:id}).then(res => {
				let {state,msg,data}=res;
				let arr=[];
				if(state!=200){
					$this.timeline.data=arr;
					return $this.$message.error(msg);
				}
				data.forEach(d => {
					let one={};
					let choose=d.mark_info[0];
					one.time=choose.crt_date+'【'+(choose.crt_name||'')+'】';
					one.content=choose.mark_info;
					arr.push(one);
				});
				$this.timeline.data=arr;
		 	}).catch(e => {
		 		$this.$message.error(e);
		 	});
		 },
		 saveyp(id,type){
		 	let $this=this;
		 	let ypSave=[];
		 	$this.ypArr.forEach(d => {
		 		let one={};
		 		one.equipment_id=id;
		 		one.is_walkthrough=d.check?1:0;
		 		one.walkthrough_name=d.yp_name;
		 		one.walkthrough_cycle=d.yp_cycle;
		 		one.next_time=d.next_date;
		 		if(type==1)one.id=d.id;			
		 		ypSave.push(one);
		 	});
			let tab_list=JSON.stringify({"tab_list":ypSave||[]});
			if(type==0)return createNewYp({tab_list:tab_list}).then(res => {
				let {state,msg,data}=res;
				$this.showData.dialogVisible=false;
				$this.btnLoading=false;
				if(state!=200)return $this.$message.error(msg);
				$this.$message({
					message:type==0?'保存成功':'修改成功',
					type:'success'
				});
				this.$emit('OnSure',true);
			}).catch(e => {
				$this.$message.error(e);
				$this.showData.dialogVisible=false;
			});
		 	 editYp({tab_list:tab_list}).then(res => {
				let {state,msg,data}=res;
				$this.showData.dialogVisible=false;
				$this.btnLoading=false;
				if(state!=200)return $this.$message.error(msg);
				$this.$message({
					message:type==0?'保存成功':'修改成功',
					type:'success'
				});
				this.$emit('OnSure',true);
			}).catch(e => {
				$this.$message.error(e);
				$this.showData.dialogVisible=false;
			});
		 },
		 upload(){},
		 download(){
		 	let sels=this.dataTable.sels;
		 	let arr=[];
		 	sels.forEach(d => {
		 		downFj({id:d.id}).then(res => {
		 		console.log(res);
		 	}).catch(e => {
		 		$this.$message.error(e);
		 	});
		 	});
		 },
		 deletefile(){
		 	var $this=this;
				$this.$confirm('确定删除这些附件么么？','操作提示',{type:'warning'}).then((a) => {
					let arr=[];
					$this.dataTable.sels.forEach(d => {
						arr.push(d.id);
					})
					deleteFj({id:arr.join(',')}).then(res => {
						let {msg,state}=res;
						if(state!=200){
							$this.$message({
								message:msg,
								type:'error'
							});
							return false;
						}
						$this.$message({
				          message: '删除成功',
				          type: 'success'
				        });
						$this.getfj();
					}).catch(e => {
						$this.$message({
							message:e,
							type:'error'
						});
					});
				});
		 },
		 getOneEquip(id){
		 	let $this=this;
		 	oneEquip({id:id}).then(res => {
		 		let {state,msg,data}=res;
		 		if(state!=200)return false;
		 		data.yp_list.forEach(d => {
		 			d.check=d.is_walkthrough==1;
		 			d.start_date=d.crt_date;
		 			d.yp_name=d.walkthrough_name;
		 			d.yp_cycle=d.walkthrough_cycle;
		 			d.next_date=d.next_time;
		 		});
		 		$this.ypArr=data.yp_list;
		 	}).catch(e => {
				$this.$message.error(e);
		 	});
		 },
		 getlbArr(){
		 	let para={
		 		pageIndex:1,
		 		pageNum:999,
		 		stop_flag:1,
		 		coding_type:20001
		 	};
		 	let $this=this;
		 	getBmPerson(para).then(res => {
				$this.lbArr= res.data.list||[];			 		
		 	}).catch(e => {
		 		$this.lbArr=[];
		 	})
		 },
		 getcjArr(){
		 	let para={
		 		pageIndex:1,
		 		pageNum:999,
		 		name:''
		 	};
		 	let $this=this;
		 	getBmPersonPr(para).then(res => {
				$this.cjArr= res.data.list||[];			 		
		 	}).catch(e => {
		 		$this.cjArr=[];
		 	})
		 },
		 getksArr(){
		 	let para={
		 		pageIndex:1,
		 		pageNum:999,
		 		stop_flag:1
		 	};
		 	let $this=this;
		 	getBmPersonDeG(para).then(res => {
				$this.ksArr= res.data.list||[];			 		
		 	}).catch(e => {
		 		$this.ksArr=[];
		 	});
		 },
		 getypArr(){
		 	let para={
		 		hospital_id:'',
		 		coding_type:20003,
		 		is_walkthrough:1
		 	}
		 	let $this=this;
		 	$this.ypArr=[];
		 	getBmPersonYp(para).then(res => {
		 		res.data.forEach(d => {
		 			d.start_date=new Date();
		 		});
				$this.ypArr= res.data||[];
				this.getOneEquip(this.form.id);
		 	}).catch(e => {
		 		$this.ypArr=[];
		 	});
		 },
		    handleNodeClick(data) {
		     },
			calcTitle(){
				let $this=this;
				return $this.showData.type==0?'新增科室':'科室详情编辑';
			},
			departChange(val){
				let selected=this.ksArr.filter(d => d.id==val)[0]||'';
				if(selected) this.form.department_name=selected.department_name;
				else this.form.department_name='';
			}
		},
		mounted(){
			
		},
		beforeUpdate(){
			this.activeName='first';
			if(this.showData.openFlag){
				this.bottom=1;
				this.getlbArr();
				this.getcjArr();
				this.getksArr();
				this.getypArr();
				this.showData.openFlag=false;
			}
			if(!this.showData.freshFlag){
				if(this.$refs.form)this.$refs.form.resetFields();
				this.form=Object.assign({
					sb_name:'',
					sb_ggxh:'',
					sb_code:'',
					sb_zzlb_id:'',
					sb_yt_id:'',
					sb_szcs:'',
					scrap_date:'',
					sb_ly:'',
					sb_qx:'',
					is_scrap:0,
					sb_eddy:'',
					sb_manufacturer_id:'',
					sb_syks_id:'',
					flag_fszlxkz:0,
					fszlxkz_date:'',
					flag_fsaqxkz:0,
					fsaqxkz_date:'',
					flag_dxpzxkz:0,
					dxpzxkz_date:'',
					department_name:''
				},this.showData.form||{});
				this.showData.freshFlag=1;
			}
		}
	}
</script>

<style>
	
.equip_dia .el-dialog__body{
		padding:10px 20px;
	}
	.equip_dia .el-dialog{
		width:1000px;
	}
	.equip_dia .my-form-item-check .el-form-item__content{
		margin-left: 75px;
	}
	.equip_dia .my-form-item-check .el-checkbox{
		text-align: left;
	}
	
	.el-timeline {
    margin: 0;
    font-size: 14px;
    list-style: none;
}
.el-timeline-item {
    position: relative;
    padding-bottom: 20px;
}
.el-timeline-item__wrapper {
    position: relative;
    padding-left: 28px;
    top: -3px;
}
.el-timeline-item__content {
    color: #303133;
}
.el-timeline-item__timestamp{
	word-break: break-all;
}
.el-timeline-item__timestamp.is-bottom {
    margin-top: 8px;
}
.el-timeline-item__timestamp {
    color: #909399;
    line-height: 1;
    font-size: 13px;
}
.el-timeline-item__tail {
    position: absolute;
    left: 4px;
    height: 100%;
    border-left: 2px solid #e4e7ed;
}
.el-timeline-item__node--normal {
    left: -1px;
    width: 12px;
    height: 12px;
}
.el-timeline-item__node {
    position: absolute;
    background-color: #e4e7ed;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
}
.el-timeline .el-timeline-item:last-child .el-timeline-item__tail {
    display: none;
}
.my-form-item .el-select{
	width:100%;
}
</style>