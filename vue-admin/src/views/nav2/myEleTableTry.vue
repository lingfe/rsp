<template>
	<section>
		<my-el-table ref="tableclick" :table="dataTable" 
			@onRowClick='rowClick' 
			@onHandleSelectionChange='HandleSelectionChange'
			@onCurrentChange='currentChange'
			@handleSubmit='handleSubmit'
			@onSizeChange='sizeChange'
			@onRefresh='refresh'
			></my-el-table>
	</section>
</template>
<script>
	import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';
	
export default{
	data(){
		return {
			dataTable:{
				hasPagination:true,
				fixed:'left',
				maxHeight:600,
				hasShowSummary:true,
				loading:false,
				pageSize:20,
				pageIndex:1,
				sels:[],
				defaultWidth:100,
				hasOperation:true,
				operation: {             // 操作功能
	              label: '操作',                // 操作列的行首文字
		              width: '120',                // 操作列的宽度
		              className: '',               // 操作列的class名
		              data: [                      // 操作列数据
		                {
		                  label: '编辑',                // 按钮文字
		                  Fun: 'handleSubmit',         // 点击按钮后触发的父组件事件
		                  size: 'small',                // 按钮大小
		                  id: '2'                    // 按钮循环组件的key值
		                }
	              ]
	            },
				tr:[
					{label:'姓名',prop:'name',width:120},
					{label:'性别',prop:'sex',width:100},
					{label:'年龄',prop:'age',width:100},
					{label:'生日',prop:'birth',width:120},
					{label:'生日',prop:'birth',width:120},
					{label:'生日',prop:'birth',width:120},
					{label:'生日',prop:'birth',width:120},
					{label:'生日',prop:'birth',width:120},
					{label:'生日',prop:'birth',width:120},
					{label:'生日',prop:'birth',width:120},
					{label:'地址',prop:'addr','minWidth':180},
					],
				data:[],
				getSummaries(param) {
			        const { columns, data } = param;
			        const sums = [];
			        columns.forEach((column, index) => {
			          if(index===1){
			          	sums[index] = '合计：';
			            return;
			          }
			          if(index===3){
			          	 const values = data.map(item => Number(item[column.property]));
			          	 sums[index]=values.reduce((p,c) => {return p+c},0);
			          	 return sums[index];
			          }
			          sums[index]='';
			          return sums[index];
			        });
			          return sums;
				}
			}
		}
	},
	methods:{
		rowClick(index,data){
			this.$refs.tableclick.toggleSelection([data.row]);
		},
		HandleSelectionChange(sels){
			this.sels = sels;
		},
		getUsers() {
				let para = {
					page: this.dataTable.pageIndex,
					pageSize:this.dataTable.pageSize
				};
				this.dataTable.loading = true;
				getUserListPage(para).then((res) => {
					this.dataTable.data = res.data.users;
					this.dataTable.total = res.data.total;
					this.dataTable.loading = false;
				});
		},
		handleSubmit(a,b){
			console.log(a,b);
		},
		currentChange(val){
			this.dataTable.pageIndex=val;
			this.getUsers();
		},
		sizeChange(val){
			this.dataTable.pageSize=val;
			this.getUsers();
		},
		refresh(){
			this.getUsers();
		}
	},
	mounted(){
		this.getUsers();
	}
}
</script>