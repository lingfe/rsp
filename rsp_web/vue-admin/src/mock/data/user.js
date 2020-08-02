import Mock from 'mockjs';
const LoginUsers = [
  {
    id: 1,
    username: 'admin',
    password: '123456',
    avatar: 'static/img/user.png',
    name: '张某某'
  }
];

const Users = [],Bm=[];
var MockRandom = Mock.Random;
MockRandom.extend({
	uname:function(){
		var unames=['张三','李四','王五','赵六'];
		return this.pick(unames);
	},
	bmname:function(){
		var name=['男','女','主任医生','副主任医生','医生','护士'];
		return this.pick(name);
	}
});
for (let i = 0; i < 86; i++) {
  Users.push(Mock.mock({
    id: Mock.Random.guid(),
    name: Mock.Random.cname(),
    addr: Mock.mock('@county(true)'),
    'age|18-60': 1,
    birth: Mock.Random.date(),
    sex: Mock.Random.integer(0, 1)
  }));
  Bm.push(Mock.mock({
    id: Mock.Random.guid(),
    name: Mock.Random.bmname(),
    bz: '备注',
    create_man: Mock.Random.uname(),
    create_date: Mock.Random.date(),
    stop_flag: Mock.Random.integer(0, 1),
    stop_man:Mock.Random.uname(),
    stop_date:Mock.Random.date()
  }));
}

export { LoginUsers, Users,Bm };
