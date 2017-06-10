using System.Collections;
using System.Collections.Generic;
using System.ServiceModel;
using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.SL.ServiceLayerContracts
{
    [ServiceContract]
    public interface IOrderService
    {
        [OperationContract]
        void Create(OrderDTO orderDTO);

        IEnumerable<OrderDTO> GetAll();
    }
}
