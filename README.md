# Cash Register
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## 资料
[功能参考视频](http://v.youku.com/v_show/id_XMjc0MTk4Mzc5Ng==.html?spm=a2h3j.8428770.3416059.1)

[功能查询文档](http://wiki.pospal.cn/)

[参考版下载地址](http://www.pospal.cn/downcenter.html)

## 功能
![](https://github.com/huhuics/Accumulate/blob/master/image/%E6%94%B6%E9%93%B6%E7%B3%BB%E7%BB%9F%E5%8A%9F%E8%83%BD%E5%9B%BE.png)

## 树状结构存储方式
如以下树状结构：
```html
├── a
│   ├── d
│   │   ├── p
│   │   ├── q
│   │   └── r
│   ├── e
│   └── f
├── b
│   ├── x
│   ├── y
│   └── z
├── c
```

对应的数据库表值为：

```html
| id | value | parent_id | key   | level |                                
| 1  | a     | 0         | "-"    | 1     |
| 2  | b     | 0         | "-"    | 1     |
| 3  | c     | 0         | "-"    | 2     |
| 4  | d     | 1         | "1-"   | 2     |
| 5  | e     | 1         | "1-"   | 2     |
| 6  | f     | 1         | "1-"   | 2     |
| 7  | x     | 2         | "2-"   | 2     |
| 8  | y     | 2         | "2-"   | 2     |
| 9  | z     | 2         | "2-"   | 2     |
| 10 | p     | 4         | "1-4-" | 3     |
| 11 | q     | 4         | "1-4-" | 3     |
| 12 | r     | 4         | "1-4-" | 3     |
```

+ id：本节点的primary key

+ parent_id：其值为父节点的primary key

+ key：可以称为线索,从跟节点到父节点的primary key，中间用任意非数字符号分割

+ level：表示当前节点到根节点的距离

于是，在给定一个节点d的时候：

+ 查找d的所有子孙节点

```SQL
select * from table_name where key like "${d.key}${d.id}-%"
```

+ 查找子节点

```SQL
select * from table_name where key like "${d.key}${d.id}-%" and level=${d.level}+1
```

