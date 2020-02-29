package com.daxiang.service;

import com.daxiang.mbg.mapper.DeviceMapper;
import com.daxiang.model.PageRequest;
import com.daxiang.model.Response;
import com.daxiang.model.vo.DeviceVo;
import com.github.pagehelper.PageHelper;
import com.daxiang.mbg.po.Device;
import com.daxiang.mbg.po.DeviceExample;
import com.daxiang.model.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jiangyitao.
 */
@Service
@Slf4j
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 保存手机信息
     *
     * @param device
     * @return
     */
    public Response save(Device device) {
        // 数据库是否有该手机
        Device dbDevice = deviceMapper.selectByPrimaryKey(device.getId());
        int saveRow;
        if (dbDevice == null) {
            // 首次接入的device
            saveRow = deviceMapper.insertSelective(device);
        } else {
            // 更新Device
            saveRow = deviceMapper.updateByPrimaryKeySelective(device);
        }
        return saveRow == 1 ? Response.success("保存成功") : Response.fail("保存失败，请稍后重试");
    }

    /**
     * 查询设备列表
     *
     * @param device
     * @param pageRequest
     * @return
     */
    public Response list(Device device, PageRequest pageRequest) {
        boolean needPaging = pageRequest.needPaging();
        if (needPaging) {
            PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        }

        List<Device> devices = selectByDevice(device);
        List<DeviceVo> deviceVos = devices.stream().map(d -> {
            DeviceVo deviceVo = new DeviceVo();
            BeanUtils.copyProperties(d, deviceVo);
            return deviceVo;
        }).collect(Collectors.toList());

        if (needPaging) {
            long total = Page.getTotal(devices);
            return Response.success(Page.build(deviceVos, total));
        } else {
            return Response.success(deviceVos);
        }
    }

    public List<Device> selectByDevice(Device device) {
        DeviceExample example = new DeviceExample();
        DeviceExample.Criteria criteria = example.createCriteria();

        if (device != null) {
            if (!StringUtils.isEmpty(device.getId())) {
                criteria.andIdEqualTo(device.getId());
            }
            if (!StringUtils.isEmpty(device.getName())) {
                criteria.andNameEqualTo(device.getName());
            }
            if (!StringUtils.isEmpty(device.getAgentIp())) {
                criteria.andAgentIpEqualTo(device.getAgentIp());
            }
            if (device.getAgentPort() != null) {
                criteria.andAgentPortEqualTo(device.getAgentPort());
            }
            if (device.getPlatform() != null) {
                criteria.andPlatformEqualTo(device.getPlatform());
            }
            if (device.getStatus() != null) {
                criteria.andStatusEqualTo(device.getStatus());
            }
        }
        example.setOrderByClause("status desc,create_time desc");

        return deviceMapper.selectByExample(example);
    }

    /**
     * 开始控制手机
     *
     * @param deviceId
     * @return
     */
    public Response start(String deviceId) {
        if (StringUtils.isEmpty(deviceId)) {
            return Response.fail("设备id不能为空");
        }

        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        if (device == null) {
            return Response.fail("手机不存在");
        }
        if (device.getStatus() != Device.IDLE_STATUS) {
            return Response.fail("手机未闲置");
        }

        return Response.success();
    }

    public Response getOnlineDevices(Integer platform) {
        DeviceExample deviceExample = new DeviceExample();
        DeviceExample.Criteria criteria = deviceExample.createCriteria();
        criteria.andStatusNotEqualTo(Device.OFFLINE_STATUS);
        if (platform != null) {
            criteria.andPlatformEqualTo(platform);
        }
        return Response.success(deviceMapper.selectByExample(deviceExample));
    }

    public List<Device> getOnlineDevicesByAgentIp(String agentIp) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andAgentIpEqualTo(agentIp).andStatusNotEqualTo(Device.OFFLINE_STATUS);
        return deviceMapper.selectByExample(deviceExample);
    }

    public int updateByAgentIp(Device device, String agentIp) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andAgentIpEqualTo(agentIp);
        return deviceMapper.updateByExampleSelective(device, deviceExample);
    }
}
