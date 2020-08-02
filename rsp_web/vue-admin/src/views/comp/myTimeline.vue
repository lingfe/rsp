<template :data='showData'>
	<ul class="el-timeline">
		<li class="el-timeline-item" v-for='(item,i) in showData.data'>
			<div class="el-timeline-item__tail"></div>
			<div class="el-timeline-item__node el-timeline-item__node--normal el-timeline-item__node--"></div>
			<div class="el-timeline-item__wrapper">
				<div class="el-timeline-item__content">
					{{item.time}}
    			</div>
    			<div class="el-timeline-item__timestamp is-bottom">
    				{{item.content}}
    			</div>
			</div>
		</li>
	</ul>
</template>

<script>
	export default{
		props:{
			showData:{
				type:Object,
				default(){
					return {
						data:[]
					}
				}
			}
		}
	}
</script>

<style>
</style>