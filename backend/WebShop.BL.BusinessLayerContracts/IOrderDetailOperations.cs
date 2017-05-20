using System.Collections.Generic;

using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerContracts
{
    interface IOrderDetailOperations
    {
        void Create(OrderDetailsDTO orderDetail);
        OrderDetailsDTO Get(int orderDetailId);
    }
}
