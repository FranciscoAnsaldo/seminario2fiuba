package eventhunter

import grails.gorm.services.Service

@Service(Alert)
interface AlertService {

    Alert get(Serializable id)

    List<Alert> list(Map args)

    Long count()

    void delete(Serializable id)

    Alert save(Alert alert)

}