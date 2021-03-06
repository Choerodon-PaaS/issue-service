package io.choerodon.issue.fixdata.feign;

import io.choerodon.issue.fixdata.dto.StatusForMoveDataDO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author shinan.chen
 * @date 2018/10/25
 */
@FeignClient(value = "state-machine-service", fallback = FixStateMachineFeignClientFallback.class)
@Component
public interface FixStateMachineFeignClient {

    /**
     * 修复创建项目默认敏捷状态机和测试状态机
     *
     * @param organizationId
     * @param projectCode
     * @param statuses
     * @return
     */
    @RequestMapping(value = "/v1/fix_data/create_state_machine_AG_TE", method = RequestMethod.POST)
    ResponseEntity<Map<String, Long>> createAGStateMachineAndTEStateMachine(@RequestParam(value = "organization_id") Long organizationId,
                                                                            @RequestParam(value = "project_code") String projectCode,
                                                                            @RequestBody List<String> statuses);

    /**
     * 根据敏捷状态数据，修复创建状态
     *
     * @param statusForMoveDataDOList
     * @return
     */
    @RequestMapping(value = "/v1/fix_data/create_status", method = RequestMethod.POST)
    void createStatus(@RequestBody List<StatusForMoveDataDO> statusForMoveDataDOList);


    /**
     * 修复创建组织默认状态机
     *
     * @param organizationId
     */
    @RequestMapping(value = "/v1/fix_data/create_default_state_machine", method = RequestMethod.GET)
    void createDefaultStateMachine(@RequestParam(value = "organization_id") Long organizationId);

}
