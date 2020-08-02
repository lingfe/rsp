import dialogCus from './dialogcus.vue'
import MyTable from './mytable.vue'
import MyElTable from './myeltable.vue'
import MyCheckGroup from './mycheckgroup.vue'
import MyRaido from './myRadio.vue'
import MySelect from './myselect.vue'
import MySelectas from './asmyselect.vue'
import MySelectMultiple from './myselect_multiple.vue'
import NewBmDia from '../bmgl/new_bm.vue'
import NewAreaDia from '../bmgl/new_area.vue'
import Dialog_progress from '../diatemp/dia_new_progress.vue'
import Dialog_progress2 from '../diatemp/dia_progress.vue'
import Dialog_depart from '../departgl/newdepart.vue'
import EquipDialog from '../equipgl/equipDialog.vue'
import MyTimeline from './myTimeline.vue'
import Vue from 'vue'

export default {
	install: (vue) =>{
		Vue.component('dia-cus',dialogCus);
		Vue.component('my-table',MyTable);
		Vue.component('my-el-table',MyElTable);
		Vue.component('my-checkgroup',MyCheckGroup);
		Vue.component('my-radio',MyRaido);
		Vue.component('my-select',MySelect);
		Vue.component('my-select-as',MySelectas);
		Vue.component('my-select-multiple',MySelectMultiple);
		Vue.component('my-new-bm',NewBmDia);
		Vue.component('my-new-area',NewAreaDia);
		Vue.component('my-new-progress',Dialog_progress);
		Vue.component('my-progress',Dialog_progress2);
		Vue.component('my-timeline',MyTimeline);
		Vue.component('my-new-depart',Dialog_depart);
		Vue.component('my-new-equip',EquipDialog);
	}
}
