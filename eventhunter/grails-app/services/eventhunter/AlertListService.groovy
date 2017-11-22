package eventhunter

import grails.gorm.services.Service

@Service(AlertList)
interface AlertListService {

    AlertList get(Serializable id)

    List<AlertList> list(Map args)

    Long count()

    void delete(Serializable id)

    AlertList save(AlertList alertList)

}