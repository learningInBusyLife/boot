package com.lick.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: RedisAPI，使用Spring-data-redis封装了键（Key）、字符串（String）、列表（List）、哈希（Hash）、集合（Set）等操作
 * @ClassName: SpringRedisAPI.java
 * @Package: com.yuntai.med.support.cache.redis
 * @Author: lick
 * @Date: 2015年11月13日 上午10:15:49
 * @Copyright: 版权归 lick 所有
 * <ModifyLog>
 * @ModifyContent:
 * @Author:
 * @Date: </ModifyLog>
 */
@Component
public class SpringRedisAPI {
    private static final Logger logger = LoggerFactory.getLogger(SpringRedisAPI.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *
     */
    public SpringRedisAPI() {
    }

    public SpringRedisAPI(StringRedisTemplate redisTemplate) {
        this.stringRedisTemplate = redisTemplate;
    }

    /**
     * @param key
     * @param hashKey
     * @param value
     * @Description: Hash（哈希表） ： 将哈希表 key 中的域 field 的值设为 value 。
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:16:06
     */
    public void hSet(final String key, String hashKey, Object value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * @param key
     * @param field
     * @return
     * @Description: Hash（哈希表） ： 返回哈希表 key 中给定域 field 的值。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:16:27
     */
    public Object hGet(final String key, String field) {
        return stringRedisTemplate.opsForHash().get(key, field);
    }

    /**
     * @param key
     * @param map
     * @Description: Hash（哈希表） ： 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * 此命令会覆盖哈希表中已存在的域。
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:16:19
     */
    public void hMSet(final String key, Map<Object, Object> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * @param key
     * @param fields
     * @return
     * @Description: Hash（哈希表） ： 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * @Author: lick
     * @Date: 2015年11月14日 下午2:49:39
     */
    public List<Object> hMGet(final String key, Collection<Object> fields) {
        return stringRedisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * @param key
     * @return
     * @Description: Hash（哈希表） ： 返回哈希表 key 中，所有的域和值。
     * 在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:16:34
     */
    public Map<Object, Object> hGetAll(final String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * @param key
     * @return
     * @Description: Hash（哈希表） ：  返回哈希表 key 中所有域的值。
     * @Author: lick
     * @Date: 2015年11月14日 下午2:56:59
     */
    public List<Object> hVals(final String key) {
        return stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * @param key
     * @param field
     * @return
     * @Description: Hash（哈希表） ： 查看哈希表 key 中，给定域 field 是否存在。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:16:43
     */
    public boolean hExists(final String key, Object field) {
        return stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * @param key
     * @param ojbKeys
     * @Description: Hash（哈希表） ： 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:16:50
     */
    public void hDel(final String key, Object... ojbKeys) {
        stringRedisTemplate.opsForHash().delete(key, ojbKeys);
    }

    public Set<Object> hKeys(final String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     * @param key
     * @return
     * @Description: Hash（哈希表） ：  返回哈希表 key 中域的数量
     * @Author: lick
     * @Date: 2015年11月14日 下午2:53:33
     */
    public long hLen(final String key) {
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * @param key
     * @return 当 key 不存在时，返回 nil ，否则，返回 key 的值。如果 key 不是字符串类型，那么返回一个错误。
     * @Description: String（字符串） ： 返回 key 所关联的字符串值。
     * 如果 key 不存在那么返回特殊值 nil 。
     * 假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:16:56
     */
    public String get(final String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return
     * @Description: String（字符串） ： 返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * 负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
     * getRange 通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求
     * @Author: lick
     * @Date: 2015年11月14日 下午3:29:18
     */
    public String getRange(final String key, long start, long end) {
        return stringRedisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * @param key
     * @param value
     * @return 返回给定 key 的旧值。当 key 没有旧值时，也即是， key 不存在时，返回 nil 。
     * @Description: String（字符串） ： 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
     * @Author: lick
     * @Date: 2015年11月14日 下午3:31:15
     */
    public String getSet(final String key, String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * @param key
     * @param value
     * @Description: String（字符串） ： 将字符串值 value 关联到 key
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:17:03
     */
    public void set(final String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * @param key
     * @param value
     * @param seconds
     * @Description: String（字符串） ： 将字符串值 value 关联到 key ，并设置生命周期，以秒为单位
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:17:12
     */
    public void set(final String key, String value, int seconds) {
        stringRedisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * @param key
     * @param value
     * @return 追加 value 之后， key 中字符串的长度。
     * @Description: String（字符串） ：  如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     * @Author: lick
     * @Date: 2015年11月14日 下午3:34:29
     */
    public int append(final String key, String value) {
        return stringRedisTemplate.opsForValue().append(key, value);
    }

    /**
     * @param key
     * @param values
     * @return
     * @Description: List（列表） ： 将一个或多个值 value 插入到列表 key 的表头
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，
     * 这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:17:21
     */
    public Long lPush(final String key, String... values) {
        return stringRedisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * @param key
     * @param values
     * @return
     * @Description: List（列表） ： 将一个或多个值 value 插入到列表 key 的表头
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，
     * 这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * @Author: lick
     * @Date: 2015年11月14日 下午3:16:44
     */
    public Long lPush(final String key, Collection<String> values) {
        return stringRedisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * @param key
     * @return 列表的头元素。 当 key 不存在时，返回 nil 。
     * @Description: List（列表） ： 移除并返回列表 key 的头元素。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:17:40
     */
    public String lPop(final String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * @param key
     * @param values
     * @return
     * @Description: List（列表） ： 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，
     * 得出的结果列表为 a b c ，	等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。
     * @Author: lick
     * @Date: 2015年11月14日 下午3:14:16
     */
    public Long rPush(final String key, String... values) {
        return stringRedisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * @param key
     * @param values
     * @return
     * @Description: List（列表） ： 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，
     * 得出的结果列表为 a b c ，	等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。
     * @Author: lick
     * @Date: 2015年11月14日 下午3:14:16
     */
    public Long rPush(final String key, Collection<String> values) {
        return stringRedisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * @param key
     * @return
     * @Description: List（列表） ： 移除并返回列表 key 的尾元素。
     * @Author: lick
     * @Date: 2015年11月14日 下午3:12:07
     */
    public String rPop(final String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }


    /**
     * @param key
     * @return
     * @Description: List（列表） ： 返回列表 key 的长度。
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     * @Author: lick
     * @Date: 2015年11月13日 上午11:18:16
     */
    public Long lLen(final String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /**
     * @Description: Set（集合）：返回集合key的长度，如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     * @Author: qintao@lick.com
     * @Date: 2017/7/10 15:33
     * @Copyright: 版权归 lick 所有
     */
    public Long sLen(final String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * @param key
     * @param start
     * @param stop
     * @return
     * @Description: List（列表） ： 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * 【注意LRANGE命令和编程语言区间函数的区别】假如你有一个包含一百个元素的列表，对该列表执行 LRANGE list 0 10 ，结果是一个包含11个元素的列表，这表明 stop 下标也在 LRANGE 命令的取值范围之内(闭区间)，这和某些语言的区间函数可能不一致，
     * 比如Ruby的 Range.new 、 Array#slice 和Python的 range() 函数。
     * 【超出范围的下标】超出范围的下标值不会引起错误。如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，那么 LRANGE 返回一个空列表。如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:19:03
     */
    public List<String> lRange(final String key, int start, int stop) {
        return stringRedisTemplate.opsForList().range(key, start, stop);
    }

    /**
     * @param key
     * @param count
     * @param value
     * @return 被移除元素的数量, 因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
     * @Description: List（列表） ： 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     * count 的值可以是以下几种：
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
     * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
     * count = 0 : 移除表中所有与 value 相等的值。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:19:11
     */
    public long lRem(final String key, long count, String value) {
        return stringRedisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * @param key
     * @param index
     * @return 列表中下标为 index 的元素。 如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
     * @Description: List（列表）： 返回列表 key 中，下标为 index 的元素。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * 如果 key 不是列表类型，返回一个错误。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:19:18
     */
    public String lIndex(final String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * @param key
     * @param index
     * @param value
     * @Description: List（列表）： 将列表 key 下标为 index 的元素的值设置为 value 。
     * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:19:36
     */
    public void lSet(final String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    /**
     * @param key
     * @param start
     * @param end
     * @Description: List（列表）: 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:19:47
     */
    public void lTrim(final String key, long start, int end) {
        stringRedisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * @param key
     * @param values
     * @Description: Set（集合）： 将一个或多个元素加入到集合key中，已经存在于集合中的member元素将被忽略
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
     * 当 key 不是集合类型时，返回一个错误。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:25:06
     */
    public void sAdd(final String key, String... values) {
        stringRedisTemplate.opsForSet().add(key, values);
    }

    /**
     * @param key
     * @Description: Set（集合）： 移除并返回集合的一个随机元素，如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用 sRandMember()方法。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:30:34
     */
    public String sPop(final String key) {
        return stringRedisTemplate.opsForSet().pop(key);
    }

    /**
     * @param key
     * @param count
     * @return
     * @Description: Set（集合）： 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。
     * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。
     * 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值
     * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。
     * @Author: lick
     * @Date: 2015年11月13日 上午11:35:46
     */
    public Set<String> sRandMember(final String key, long count) {
        return stringRedisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * @param key
     * @param otherKey
     * @Description: Set（集合）： 返回一个集合的全部成员，该集合是所有给定集合之间的差集。
     * 不存在的 key 被视为空集。
     * @Author: lick
     * @Date: 2015年11月13日 下午1:29:51
     */
    public Set<String> sDiff(final String key, String otherKey) {
        return stringRedisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     * @Description: Set（集合）： 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地返回结果集。
     * 如果 destination 集合已经存在，则将其覆盖。
     * destination 可以是 key 本身。
     * @Author: lick
     * @Date: 2015年11月13日 下午1:32:16
     */
    public long sDiffStore(final String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    /**
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     * @Description: Set（集合）： 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地返回结果集。
     * 如果 destination 集合已经存在，则将其覆盖。
     * destination 可以是 key 本身。
     * @Author: lick
     * @Date: 2015年11月13日 下午1:37:11
     */
    public long sDiffStore(final String key, Collection<String> otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    /**
     * @param key
     * @param member
     * @return
     * @Description: Set（集合）： 判断 member 元素是否集合 key 的成员
     * @Author: lick
     * @Date: 2015年11月13日 下午1:39:14
     */
    public boolean sIsMember(final String key, Object member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * @param key
     * @return
     * @Description: Set（集合）： 返回集合 key 中的所有成员。 不存在的 key 被视为空集合。
     * @Author: lick
     * @Date: 2015年11月13日 下午1:46:31
     */
    public Set<String> sMembers(final String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * @param source
     * @param member
     * @param destination
     * @return
     * @Description: Set（集合）： 将 member 元素从 source 集合移动到 destination 集合
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。
     * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
     * 当 source 或 destination 不是集合类型时，返回一个错误。
     * 如果 member 元素被成功移除，返回 1 。
     * 如果 member 元素不是 source 集合的成员，并且没有任何操作对 destination 集合执行，那么返回 0 。
     * @Author: lick
     * @Date: 2015年11月13日 下午1:50:50
     */
    public boolean sMove(final String source, String member, String destination) {
        return stringRedisTemplate.opsForSet().move(source, member, destination);
    }

    /**
     * @param key
     * @param values
     * @return
     * @Description: Set（集合）： 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     * 当 key 不是集合类型，返回一个错误
     * 返回被成功移除的元素的数量，不包括被忽略的元素。
     * @Author: lick
     * @Date: 2015年11月13日 下午1:54:59
     */
    public long sRem(final String key, Object... values) {
        return stringRedisTemplate.opsForSet().remove(key, values);
    }

    /**
     * @param key
     * @Description: Key（键）:  删除给定的一个 key 。
     * @Author: lick
     * @Date: 2015年11月13日 下午1:59:57
     */
    public void kDel(final String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * @param keys
     * @Description: Key（键）:  删除给定的多个key
     * @Author: lick
     * @Date: 2015年11月13日 下午2:00:52
     */
    public void kDel(final Collection<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    /**
     * @param key
     * @param seconds
     * @Description: Key（键）: 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     * 对一个已经带有生存时间的 key 执行 EXPIRE 命令，新指定的生存时间会取代旧的生存时间。
     * @Author: lick
     * @Date: 2015年11月13日 下午2:04:10
     */
    public boolean KExpire(final String key, int seconds) {
        return stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * @param key
     * @return
     * @Description: Key（键）: 检查给定 key 是否存在。
     * 若 key 存在，返回 1 ，否则返回 0 。
     * @Author: lick
     * @Date: 2015年11月13日 下午2:17:31
     */
    public boolean kExists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * @param pattern
     * @return
     * @Description: Key（键）: 查找所有符合给定模式 pattern 的 key 。
     * ！禁止使用*符号直接匹配！
     * KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
     * KEYS h*llo 匹配 hllo 和 heeeeello 等。
     * KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
     * 特殊符号用 \ 隔开
     * @Author: lick
     * @Date: 2015年11月13日 下午2:19:06
     */
    public Set<String> kKeys(final String pattern) {
        if (pattern == null || pattern.equals("") || pattern.equals("*")) {
            return null;
        }
        return stringRedisTemplate.keys(pattern);
    }

    /**
     * @Description: 批量读取hash表
     * 其中，入参Map<String, Set<String>> hash 为 redis key -- Set<String> (hash表的field名称)的参数
     * 按命令执行顺序返回hash表中value值
     * @Param:
     * @Return:
     * @Author: lick
     * @Date: 2017/7/10 14:51
     */
    public List<Object> hGetPipe(final Map<String, Set<String>> hash) {
        if (hash == null || hash.isEmpty()) {
            return null;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        return stringRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : hash.keySet()) {
                    Set<String> fieldSet = hash.get(key);
                    if (CollectionUtils.isEmpty(fieldSet)) {
                        continue;
                    }
                    for (String field : fieldSet) {
                        byte[] keyByte = null, fieldByte = null;
                        try {
                            keyByte = stringSerializer.serialize(key);
                            fieldByte = stringSerializer.serialize(field);
                            connection.hGet(keyByte, fieldByte);
                        } catch (Exception e) {
                            logger.error("[medicalcare-SpringRedisAPI-hGetPipe]批量读取hash表元素异常，keyByte:{}, fieldByte:{}", keyByte, fieldByte);
                        }
                    }
                }
                return null;
            }
        });
    }

    /**
     * @Description: 批量读取hash表----单个filed（多filed使用上面的方法）
     * 按命令执行顺序返回hash表中value值
     * @Param:
     * @Return:
     * @Author: lick
     * @Date: 2017/7/10 14:51
     */
    public List<Object> hGetPipe(final List<String> keyList, final String field) {
        if (keyList == null || keyList.isEmpty() || StringUtils.isBlank(field)) {
            return null;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        return stringRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] fieldByte = stringSerializer.serialize(field);
                for (String key : keyList) {
                    byte[] keyByte = null;
                    try {
                        keyByte = stringSerializer.serialize(key);
                        connection.hGet(keyByte, fieldByte);
                    } catch (Exception e) {
                        logger.error("[medicalcare-SpringRedisAPI-hGetPipe]批量读取hash表元素异常，keyByte:{}, fieldByte:{}", keyByte, fieldByte);
                    }
                }
                return null;
            }
        });
    }

    /**
     * @Description: 批量读取hash表----单个filed（多filed使用上面的方法）
     * 按命令执行顺序返回hash表中value值
     * @Param:keyList,field
     * @Return:
     * @Author: lick
     * @Date: 2017/7/10 14:51
     */
    public List<Object> hGetPipe(final Set<String> keyList, final String field) {
        if (keyList == null || keyList.isEmpty() || StringUtils.isBlank(field)) {
            return null;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        return stringRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] fieldByte = stringSerializer.serialize(field);
                for (String key : keyList) {
                    byte[] keyByte = null;
                    try {
                        keyByte = stringSerializer.serialize(key);
                        connection.hGet(keyByte, fieldByte);
                    } catch (Exception e) {
                        logger.error("[medicalcare-SpringRedisAPI-hGetPipe]批量读取hash表元素异常，keyByte:{}, fieldByte:{}", keyByte, fieldByte);
                    }
                }
                return null;
            }
        });
    }

    /**
     * @Description: 批量读取key内容的长度
     * 其中，入参List<String> 为 redis key
     * 按命令执行顺序返回hash表中value值
     * @Param:
     * @Return:
     * @Author: lick
     * @Date: 2017/7/10 14:51
     */
    public List<Object> sCardPipe(final List<String> keyList) {
        if (keyList == null || keyList.isEmpty()) {
            return null;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        return stringRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : keyList) {
                    byte[] keyByte = null;
                    try {
                        keyByte = stringSerializer.serialize(key);
                        connection.sCard(keyByte);
                    } catch (Exception e) {
                        logger.error("[medicalcare-SpringRedisAPI-hLenPipeByKey]批量读取元素异常，keyByte:{}", keyByte);
                    }
                }
                return null;
            }
        });
    }

    /**
     * @Description: 批量读取key内容set的内容set集合
     * 其中，入参List<String> 为 redis key
     * 按命令执行顺序返回hash表中value值
     * @Param:
     * @Return:
     * @Author: lick
     * @Date: 2017/7/10 14:51
     */
    public List<Object> sMembersPipe(final List<String> keyList) {
        if (keyList == null || keyList.isEmpty()) {
            return null;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        return stringRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : keyList) {
                    byte[] keyByte = null;
                    try {
                        keyByte = stringSerializer.serialize(key);
                        connection.sMembers(keyByte);
                    } catch (Exception e) {
                        logger.error("[medicalcare-SpringRedisAPI-sMembersPipe]批量读取元素异常，keyByte:{}", keyByte);
                    }
                }
                return null;
            }
        });
    }

    /**
     * @Description: 批量读取key内容hash的map
     * 其中，入参List<String> 为 redis key
     * 按命令执行顺序返回hash表中value值
     * @Param:
     * @Return:
     * @Author: lick
     * @Date: 2017/7/10 14:51
     */
    public List<Object> hGetAllPipe(final List<String> keyList) {
        if (keyList == null || keyList.isEmpty()) {
            return null;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        return stringRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : keyList) {
                    byte[] keyByte = null;
                    try {
                        keyByte = stringSerializer.serialize(key);
                        connection.hGetAll(keyByte);
                    } catch (Exception e) {
                        logger.error("[medicalcare-SpringRedisAPI-hGetAllPipe]批量读取元素异常，keyByte:{}", keyByte);
                    }
                }
                return null;
            }
        });
    }

    /**
     * @Description: 批量管道删除redis数据
     * @Return:
     * @Param:
     * @Author: lick
     * @Date: 2017/9/11 1:09
     */
    public void hDelPipe(final List<String> keyList, final String field) {
        if (keyList == null || keyList.isEmpty()) {
            return;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        stringRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : keyList) {
                    if (StringUtils.isEmpty(field)) {
                        continue;
                    }
                    byte[] keyByte = null, fieldByte = null;
                    try {
                        keyByte = stringSerializer.serialize(key);
                        fieldByte = stringSerializer.serialize(field);
                        connection.hDel(keyByte, fieldByte);
                    } catch (Exception e) {
                        logger.error("[medicalcare-SpringRedisAPI-hDelPipe]批量删除hash表元素异常，keyByte:{}, fieldByte:{}", keyByte, fieldByte);
                    }
                }
                return null;
            }
        });
    }

    /**
     * @Description: 批量写入hash表数据
     * 其中，入参Map<String, Map<String, String>> hash 为 redis key -- Map<field, value>的参数
     * @Param:
     * @Return:
     * @Author: lick
     * @Date: 2017/7/10 14:59
     */
    public void hSetPipe(final Map<String, Map<Object, Object>> hash) {
        if (hash == null || hash.isEmpty()) {
            return;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        stringRedisTemplate.executePipelined(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : hash.keySet()) {
                    Map<Object, Object> fieldMap = hash.get(key);
                    if (fieldMap == null || fieldMap.isEmpty()) {
                        continue;
                    }
                    for (Object field : fieldMap.keySet()) {
                        byte[] keyByte = null, fieldByte = null, valueByte = null;
                        try {
                            keyByte = stringSerializer.serialize(key);
                            fieldByte = stringSerializer.serialize(field.toString());
                            valueByte = stringSerializer.serialize(fieldMap.get(field).toString());
                            connection.hSet(keyByte, fieldByte, valueByte);
                        } catch (Exception e) {
                            logger.error("[medicalcare-SpringRedisAPI-hGetPipe]批量读取hash表元素异常，keyByte:{}, fieldByte:{}, valueByte:{}", keyByte, fieldByte, valueByte);
                        }
                    }
                }
                return null;
            }
        });
    }

    /**
     * @Description: 批量写入List数据, 其中，入参key 为 redis key ,List<Map<String, Object>>_List<String> valueList 为批量插入的内容(jsonStr)的参数
     * @Method:lPushPipe
     * @Params:key,valueList
     * @ReturnType:void
     * @Author:lick
     * @Date:2017/8/16
     * @Copyright: 版权归 lick 所有
     */
    public void lPushPipe(final String key, final List<Map<String, Object>> valueList) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        if (CollectionUtils.isEmpty(valueList)) {
            lPush(key, "");//空记录
            return;
        }
        final RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
        stringRedisTemplate.executePipelined(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                for (Map<String, Object> valueMap : valueList) {
                    if (valueMap == null || valueMap.isEmpty()) {
                        continue;
                    }
                    byte[] keyByte = null, fieldByte = null, valueByte = null;
                    try {
                        String value = new JSONObject(valueMap).toJSONString();
                        if (StringUtils.isBlank(value)) {
                            continue;
                        }
                        keyByte = stringSerializer.serialize(key);
                        valueByte = stringSerializer.serialize(value);
                        connection.lPush(keyByte, valueByte);
                    } catch (Exception e) {
                        logger.error("[medicalcare-SpringRedisAPI-lPushPipe]批量写入List数据异常，keyByte:{},valueByte:{}", keyByte, fieldByte, valueByte);
                    }
                }
                return null;
            }
        });
    }

    /**
     * @return the stringRedisTemplate
     */
    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    /**
     * @param stringRedisTemplate the stringRedisTemplate to set
     */
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

}
